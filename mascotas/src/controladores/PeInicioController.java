/**
 * 
 */
package controladores;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import entidades.PeImagen;
import entidades.PeMascota;
import entidades.PeMascotaBloq;
import entidades.PePersona;
import entidades.PeProvincia;
import entidades.PeRaza;
import servicios.ServicioMascota;

/**
 * controlador para listar las mascotas
 * @author Ablacio
 *
 */

@ManagedBean(name = "peInicioController")
@ApplicationScoped
public class PeInicioController extends BaseController implements Serializable {

	private static final long serialVersionUID = -1L;

	private PePersona persona;
	private List<PeMascota> listaMascotas;
	private List<PeMascotaBloq> listaMascotaBloq;
	private List<PeImagen> listaImagen;
	private PeMascota mascotaSeleccionada;
	private DefaultStreamedContent content1;
	private String detalleComentario;
	private Integer idPersonaLogeada=0;
	private String valorBuscar;

	@EJB
	private ServicioMascota servicioMascota;

	@PostConstruct
	public void inicializarComponentes() {
		inicializaListas();
		detalleComentario="";
		buscarMascotasPorTipo("PERDIDO");
		valorBuscar="";
	}

	public void inicializaListas() {
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Object idAtt=session.getAttribute("usuarioActual");
		if(idAtt!=null) {
			idPersonaLogeada=Integer.parseInt(idAtt+"");
		}
		mascotaSeleccionada = new PeMascota();
		listaImagen = new ArrayList<PeImagen>();
	}
	
	public void buscarMascotasPorTipo(String tipoBusqueda) {
		listaMascotas = new ArrayList<PeMascota>();
		listaMascotas = servicioMascota.listarPeMascotasPorTipo(tipoBusqueda);
		if (listaMascotas == null) {
			listaMascotas = new ArrayList<PeMascota>();
		} else {
			for (PeMascota p : listaMascotas) {
				List<PeImagen> listImp = servicioMascota.listarImagenesPorIdMascota(p.getIdMascota());
				if (listImp != null) {
					p.setArchivoImagenTmp(listImp.get(0).getArchivo());
				}
			}

		}
	}

	public void buscarPorFiltro() {
		listaMascotas = new ArrayList<PeMascota>();
		listaMascotas = servicioMascota.listarPeMascotasPorFiltro(valorBuscar);
		if (listaMascotas == null) {
			listaMascotas = new ArrayList<PeMascota>();
		} else {
			for (PeMascota p : listaMascotas) {
				List<PeImagen> listImp = servicioMascota.listarImagenesPorIdMascota(p.getIdMascota());
				if (listImp != null) {
					p.setArchivoImagenTmp(listImp.get(0).getArchivo());
				}
			}

		}
	}
	
	public void verDetallesMascota(PeMascota pascotaPant) {
		detalleComentario="Comentar...";
		listaImagen = new ArrayList<PeImagen>();
		listaMascotaBloq=new ArrayList<PeMascotaBloq>();
		mascotaSeleccionada = pascotaPant;
		listaImagen = servicioMascota.listarImagenesPorIdMascota(pascotaPant.getIdMascota());
		if (listaImagen == null) {
			listaImagen = new ArrayList<PeImagen>();
		}
		listaMascotaBloq=servicioMascota.listarPemascotaPorIdMascota(pascotaPant.getIdMascota());
		if(listaMascotaBloq==null) {
			listaMascotaBloq=new ArrayList<PeMascotaBloq>();
		}else {
			for (PeMascotaBloq masBloq : listaMascotaBloq) {
				PePersona personaP=servicioMascota.obtenerPeronaPorId(masBloq.getIdPersona());
				if(personaP!=null) {
					masBloq.setNombrePersonaTmp(personaP.getNombres());
					masBloq.setContactoPersonaTmp(personaP.getCelular());
				}
			}
		}
		
		PePersona personaRep=servicioMascota.obtenerPeronaReportaPorIdMascota(mascotaSeleccionada.getIdMascota());
		if(personaRep!=null) {
			mascotaSeleccionada.setContactoReporteTmp(personaRep.getCelular());
			mascotaSeleccionada.setNombreReporteTmp(personaRep.getNombres());
		}
		
		
		
		PeProvincia prov=servicioMascota.buscarProvinciPorId(mascotaSeleccionada.getProvincia());
		if(prov!=null) {
			mascotaSeleccionada.setNombreProvinciaTmp(prov.getNombre());
		}
		
		PeRaza raz=servicioMascota.buscarRazaPorId(mascotaSeleccionada.getRaza());
		if(raz!=null) {
			mascotaSeleccionada.setNombreRazaTmp(raz.getNombre());
		}
		
	}

	public String buscarPerdidos() {
		inicializaListas();
		buscarMascotasPorTipo("PERDIDO");
		return "";
	}

	public String buscarEcontrados() {
		inicializaListas();
		buscarMascotasPorTipo("ENCONTRADO");
		return "";
	}
	
	public String buscarRecuperados() {
		inicializaListas();
		buscarMascotasPorTipo("RECUPERADO");
		return "";
	}

	public StreamedContent getImage() throws IOException {
		content1 = new DefaultStreamedContent();
		FacesContext context1 = FacesContext.getCurrentInstance();
		if (context1.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return content1;
		} else {
			String id = context1.getExternalContext().getRequestParameterMap().get("idimg1");
			for (PeMascota i : listaMascotas) {
				if (id.equals(i.getIdMascota() + "") && i.getArchivoImagenTmp()!=null ) {
					content1 = new DefaultStreamedContent(new ByteArrayInputStream(i.getArchivoImagenTmp()));
					continue;
				}
			}
			return content1;
		}

	}

	public StreamedContent getImage2() throws IOException {
		content1 = new DefaultStreamedContent();
		FacesContext context1 = FacesContext.getCurrentInstance();
		if (context1.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return content1;
		} else {
			String id = context1.getExternalContext().getRequestParameterMap().get("idimg2");
			for (PeImagen i : listaImagen) {
				if (id.equals(i.getIdImagen() + "")) {
					content1 = new DefaultStreamedContent(new ByteArrayInputStream(i.getArchivo()));
					continue;
				}
			}
			return content1;
		}

	}
	
	public void comentar() {
		if(idPersonaLogeada==0) {
			agregarMensajeAdvertencia("Para comentar es necesario ingresar o registrarse");
		}else {
			PeMascotaBloq mascotaBloq=new PeMascotaBloq();
			mascotaBloq.setFechaIngreso(new Date());
			mascotaBloq.setDetalle(detalleComentario.length()>250? detalleComentario.substring(0, 245): detalleComentario);
			mascotaBloq.setIdMascota(mascotaSeleccionada.getIdMascota());
			mascotaBloq.setIdPersona(idPersonaLogeada);
			String resp=servicioMascota.guardarMascotaBloq(mascotaBloq);
			if("".equals(resp)) {
				agregarMensajeInfo("Cometario registrado");
			}else {
				agregarMensajeAdvertencia(resp);
			}
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

	public PeMascota getMascotaSeleccionada() {
		return mascotaSeleccionada;
	}

	public void setMascotaSeleccionada(PeMascota mascotaSeleccionada) {
		this.mascotaSeleccionada = mascotaSeleccionada;
	}

	public List<PeMascotaBloq> getListaMascotaBloq() {
		return listaMascotaBloq;
	}

	public void setListaMascotaBloq(List<PeMascotaBloq> listaMascotaBloq) {
		this.listaMascotaBloq = listaMascotaBloq;
	}

	public String getDetalleComentario() {
		return detalleComentario;
	}

	public void setDetalleComentario(String detalleComentario) {
		this.detalleComentario = detalleComentario;
	}

	public String getValorBuscar() {
		return valorBuscar;
	}

	public void setValorBuscar(String valorBuscar) {
		this.valorBuscar = valorBuscar;
	}

	
	
}
