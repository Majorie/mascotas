/**
 * 
 */
package controladores;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import entidades.PeImagen;
import entidades.PeMascota;
import entidades.PePersona;
import servicios.ServicioMascota;

/**
 * 
 * @author MTorres
 * 
 */

@ManagedBean(name = "peReportarController")
@ApplicationScoped
public class PeReportarController extends BaseController implements Serializable {

	private static final long serialVersionUID = -1L;

	private PePersona persona;
	private List<PeMascota> listaMascotas;
	private List<PeImagen> listaImagen;
	private String banderaPanel;
	private PeMascota mascota;
	private PeMascota mascotaSeleccionada;
	private UploadedFile file;
	private DefaultStreamedContent content;
	
	@EJB
	private ServicioMascota servicioMascota;

	@PostConstruct
	public void inicializarComponentes() {
		inicializaListas();
	}

	public void inicializaListas() {
		banderaPanel = "GENERAL";
		persona = new PePersona();
		mascotaSeleccionada = new PeMascota();
		listaMascotas = new ArrayList<PeMascota>();
		listaImagen = new ArrayList<PeImagen>();
		buscarMacotasPorIdPersona();
	}

	public void buscarMacotasPorIdPersona() {
		listaMascotas = new ArrayList<PeMascota>();
		listaMascotas = servicioMascota.listarPeMascotasPorIdPersona(1);
		System.out.println("viene a buscarMacotasPorIdPersona:::" + listaMascotas.size());
	}
	
	public void pantallaPrincipal() {
		banderaPanel = "GENERAL";
	}

	public void nuevaMascota() {
		banderaPanel = "NUEVAMASCOTA";
		mascota = new PeMascota();
		mascota.setEstado("EXTRAVIADA");
	}

	public String guardarMascota() {
		System.out.println("viene a guardar0:::");
		mascota.setFechaIngreso(new Date());
		String resp = servicioMascota.crearMascota(mascota, 1);
		if ("".equals(resp)) {
			inicializaListas();
			agregarMensajeInfo("Mascota registrada");
		} else {
			agregarMensajeAdvertencia(resp);
		}
		return "";
	}

	public void mostrarImagenes(PeMascota mascotaPan) {
		banderaPanel = "IMAGENMASCOTA";
		mascotaSeleccionada = mascotaPan;
		listaImagen = new ArrayList<PeImagen>();
		listaImagen = servicioMascota.listarImagenesPorIdMascota(mascotaPan.getIdMascota());
		System.out.println("mostrarImagenes:::>>>" + listaImagen.size());
		if (listaImagen == null) {
			listaImagen = new ArrayList<PeImagen>();
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("viene a handleFileUpload>>>:::");
		file = event.getFile();
		guardarImagen();
	}
	
	public void guardarImagen() {
		try {
			InputStream input = file.getInputstream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			for (int length = 0; (length = input.read(buffer)) > 0;) {
				output.write(buffer, 0, length);
			}
			PeImagen imgimagenNueva = new PeImagen();
			imgimagenNueva.setArchivo(output.toByteArray());
			imgimagenNueva.setNombre("NombreImg");
			imgimagenNueva.setIdMascota(mascotaSeleccionada.getIdMascota());
			String resp = servicioMascota.guardarImagen(imgimagenNueva);
			if ("".equals(resp)) {
				mostrarImagenes(mascotaSeleccionada);
				agregarMensajeInfo("Imagen registrada");
			} else {
				agregarMensajeAdvertencia(resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public StreamedContent getImage() throws IOException {
		content=new DefaultStreamedContent();
		System.out.println("viene a StreamedContent 1>>>:::");
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			System.out.println("viene a StreamedContent 2>>>:::");
			return content;
		}
		else {
			System.out.println("viene a StreamedContent 3>>>:::");
			String id = context.getExternalContext().getRequestParameterMap().get("idimg");
			System.out.println("viene a StreamedContent 4>>>:::"+id);
			for (PeImagen i : listaImagen) {
				if(id.equals(i.getIdImagen()+"")){
					System.out.println("viene a StreamedContent 5>>>:::");
					content= new DefaultStreamedContent(new ByteArrayInputStream(i.getArchivo()) );
					continue;
				}
			}		
			return content;
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

	public String getBanderaPanel() {
		return banderaPanel;
	}

	public void setBanderaPanel(String banderaPanel) {
		this.banderaPanel = banderaPanel;
	}

	public PeMascota getMascota() {
		return mascota;
	}

	public void setMascota(PeMascota mascota) {
		this.mascota = mascota;
	}

}
