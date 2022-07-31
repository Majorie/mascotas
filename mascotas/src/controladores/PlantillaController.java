/**
 * 
 */
package controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import entidades.PePersona;
import servicios.ServicioMascota;
import util.Util;

/**
 * pantalla para manejo de opciones de menú
 * @author JWQuispe
 * 
 */

@ManagedBean(name = "plantillaController")
@SessionScoped
public class PlantillaController extends BaseController implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String usuario;
	private String contrasenia;
	private String nombres;
	private boolean logeado = false;
	private String banderaGeneral;
	private Util util = new Util();

	@EJB
	private ServicioMascota servicioMascota;

	@PostConstruct
	public void inicializarComponentes() {
		banderaGeneral = "";
	}

	public String entrar() {
		String pant = "";

		PePersona persona = new PePersona();
		contrasenia = util.getMD5(contrasenia);
		persona = servicioMascota.obtenerPeronaPorUsuarioCOntrasenia(usuario, contrasenia);
		if (persona == null) {
			agregarMensajeAdvertencia("Credenciales no válidas, no identificado");
			return "";
		}

		if (usuario != null && usuario.equals(persona.getIdentificacion()) && contrasenia != null
				&& contrasenia.equals(persona.getContrasenia())) {
			logeado = true;
			agregarMensajeInfo("Bienvenido " + persona.getNombres());
		} else {
			logeado = false;
			agregarMensajeAdvertencia("Credenciales no válidas");
		}
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("estaLogeado", logeado);
		session.setAttribute("usuarioActual", persona.getIdPersona());
		this.nombres = persona.getNombres();
		if (logeado) {
			pant = "index.xhtml";
		}
		return pant;
	}

	public String salir() {
		logeado = false;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.removeAttribute("estaLogeado");
		session.removeAttribute("usuarioActual");
		session.invalidate();

		// HttpSession session2 =
		// (HttpSession)FacesContext.getCurrentInstance().getApplication();
		// session2.invalidate();
		agregarMensajeInfo("Hasta pronto");
		return "index.xhtml";
	}

	public String paginaInicio() {
		String paginaReserva = "index.xhtml";
		return paginaReserva;
	}

	public String logeoPePersona() {
		String paginaReserva = "peLogin.xhtml";
		return paginaReserva;
	}

	public String registrarPePersona() {
		String paginaReserva = "peRegistro.xhtml";
		return paginaReserva;
	}

	public String reportarMascota() {
		String paginaReserva = "peReportar.xhtml";
		return paginaReserva;
	}

	public String mascotaInicio() {
		String paginaReserva = "peInicio.xhtml";
		return paginaReserva;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isLogeado() {
		return logeado;
	}

	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}

	public String getBanderaGeneral() {
		return banderaGeneral;
	}

	public void setBanderaGeneral(String banderaGeneral) {
		this.banderaGeneral = banderaGeneral;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

}
