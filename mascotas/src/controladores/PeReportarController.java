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
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import entidades.PeImagen;
import entidades.PeMascota;
import entidades.PePersona;
import entidades.PeProvincia;
import entidades.PeRaza;
import servicios.ServicioMascota;

/**
 * Permite gestionar las mascotas reportadas
 * @author Ricardo Andrés Herrera Andrade
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
	UploadedFile file;
	private DefaultStreamedContent content;
	private List<PeProvincia> listaProvincias;
	private List<PeRaza> listaRazas;
	private Integer idPersonaLogeada;
	
	@EJB
	private ServicioMascota servicioMascota;

	@PostConstruct
	public void inicializarComponentes() {
		inicializaListas();
	}

	public void inicializaListas() {
		
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Object idAtt=session.getAttribute("usuarioActual");
		if(idAtt!=null) {
			idPersonaLogeada=Integer.parseInt(idAtt+"");
		}
		System.out.println("PeReportarController >>><<< idPersonaLogeada:::"+idPersonaLogeada);
		
		banderaPanel = "GENERAL";
		persona = new PePersona();
		mascotaSeleccionada = new PeMascota();
		listaMascotas = new ArrayList<PeMascota>();
		listaImagen = new ArrayList<PeImagen>();
		buscarMacotasPorIdPersona();
		cargarListas();
	}
	
	private void cargarListas() {
		listaProvincias=new ArrayList<PeProvincia>();
		listaProvincias=servicioMascota.listarProvincias();
		if(listaProvincias==null) {
			listaProvincias=new ArrayList<PeProvincia>();	
		}
		listaRazas=new ArrayList<PeRaza>();
		listaRazas=servicioMascota.listarRazas();
		if(listaRazas==null) {
			listaRazas=new ArrayList<PeRaza>();	
		}
	}

	public void buscarMacotasPorIdPersona() {
		listaMascotas = new ArrayList<PeMascota>();
		listaMascotas = servicioMascota.listarPeMascotasPorIdPersona(idPersonaLogeada);
		//System.out.println("viene a buscarMacotasPorIdPersona:::" + listaMascotas != null ? listaMascotas.size() : 0);
	}
	
	public void pantallaPrincipal() {
		banderaPanel = "GENERAL";
	}

	public void nuevaMascota() {
		banderaPanel = "NUEVAMASCOTA";
		mascota = new PeMascota();
		mascota.setEstado("PERDIDO");
	}

	public String guardarMascota() {
		System.out.println("viene a guardar0:::");
		mascota.setFechaIngreso(new Date());
		String resp = servicioMascota.crearMascota(mascota, idPersonaLogeada);
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
		System.out.println("mostrarImagenes:::>>>" + listaImagen);
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
	
	public void dejarMascota(PeMascota mascotaPant) {
		mascotaSeleccionada=mascotaPant;
	}
	
	public void actualizarMascota() {
		String resp=servicioMascota.actualizarMascota(mascotaSeleccionada);
		if ("".equals(resp)) {
			mostrarImagenes(mascotaSeleccionada);
			inicializaListas();
			buscarMacotasPorIdPersona();
			agregarMensajeInfo("Registro actualizado");
		} else {
			agregarMensajeAdvertencia(resp);
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

	

	public List<PeProvincia> getListaProvincias() {
		return listaProvincias;
	}

	public void setListaProvincias(List<PeProvincia> listaProvincias) {
		this.listaProvincias = listaProvincias;
	}

	public List<PeRaza> getListaRazas() {
		return listaRazas;
	}

	public void setListaRazas(List<PeRaza> listaRazas) {
		this.listaRazas = listaRazas;
	}

	public PeMascota getMascotaSeleccionada() {
		return mascotaSeleccionada;
	}

	public void setMascotaSeleccionada(PeMascota mascotaSeleccionada) {
		this.mascotaSeleccionada = mascotaSeleccionada;
	}
	
	

}
