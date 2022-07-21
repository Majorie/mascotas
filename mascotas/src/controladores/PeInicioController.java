/**
 * 
 */
package controladores;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import entidades.PeImagen;
import entidades.PeMascota;
import entidades.PePersona;
import servicios.ServicioMascota;

/**
 * 
 * @author ablacio
 *
 */

@ManagedBean(name = "peInicioController")
@SessionScoped
public class PeInicioController extends BaseController implements Serializable {

	private static final long serialVersionUID = -1L;

	private PePersona persona;
	private List<PeMascota> listaMascotas;
	private List<PeImagen> listaImagen;
	private PeMascota mascotaSeleccionada;
	private DefaultStreamedContent content1;

	@EJB
	private ServicioMascota servicioMascota;

	@PostConstruct
	public void inicializarComponentes() {
		inicializaListas();
	}

	public void inicializaListas() {
		mascotaSeleccionada = new PeMascota();
		listaMascotas = new ArrayList<PeMascota>();
		listaImagen = new ArrayList<PeImagen>();
		buscarMascotas();
	}

	public void buscarMascotas() {
		listaMascotas = new ArrayList<PeMascota>();
		listaMascotas = servicioMascota.listarPeMascotas();
		if (listaMascotas == null) {
			listaMascotas = new ArrayList<PeMascota>();
		} else {
			for (PeMascota p : listaMascotas) {
				List<PeImagen> listImp = servicioMascota.listarImagenesPorIdMascota(p.getIdMascota());
				if (listImp != null) {
					System.out.println("viene a buscarMascotas >>>:::"+p.getIdMascota());
					p.setArchivoImagenTmp(listImp.get(0).getArchivo());
				}
			}

		}
	}
	
	public StreamedContent getImage() throws IOException {
		content1 = new DefaultStreamedContent();
		System.out.println("viene a StreamedContent 1>>>:::");
		FacesContext context1 = FacesContext.getCurrentInstance();
		if (context1.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			System.out.println("viene a StreamedContent 2>>>:::");
			return content1;
		} else {
			System.out.println("viene a StreamedContent 3>>>:::");
			String id = context1.getExternalContext().getRequestParameterMap().get("idimg1");
			System.out.println("viene a StreamedContent 4>>>:::" + id);
			for (PeMascota i : listaMascotas) {
				if (id.equals(i.getIdMascota() + "")) {
					System.out.println("viene a StreamedContent 5>>>:::");
					content1 = new DefaultStreamedContent(new ByteArrayInputStream(i.getArchivoImagenTmp()));
					continue;
				}
			}
			return content1;
		}

	}

	public PePersona getPersona() {
		return persona;
	}

	public void setPersona(PePersona persona) {
		this.persona = persona;
	}

	public List<PeMascota> getListaMascotas() {
		return listaMascotas;
	}

	public void setListaMascotas(List<PeMascota> listaMascotas) {
		this.listaMascotas = listaMascotas;
	}

	public List<PeImagen> getListaImagen() {
		return listaImagen;
	}

	public void setListaImagen(List<PeImagen> listaImagen) {
		this.listaImagen = listaImagen;
	}

}
