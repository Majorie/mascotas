/**
 * 
 */
package controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import entidades.PePersona;
import servicios.ServicioMascota;
import util.Util;

/**
 * Pantalla paraa Registro y  Actualizacion de Usuario
 * 2022-07-28
 * @author Jlarrea
 * 
 */

@ManagedBean(name = "peRegistroController")
@SessionScoped
public class PeRegistroController extends BaseController implements Serializable {

	private static final long serialVersionUID = -1L;

	private PePersona personaNueva;
	private String banderaPantalla;
	private Util util = new Util();
	private Integer idPersonaLogeada = 0;
	private boolean banderaNuevo = true;

	@EJB
	private ServicioMascota servicioMascota;

	@PostConstruct
	public void inicializarComponentes() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Object idAtt = session.getAttribute("usuarioActual");
		if (idAtt != null) {
			idPersonaLogeada = Integer.parseInt(idAtt + "");
		}
		System.out.println("1 actualiza>>>>>>>>>>>>>" + idPersonaLogeada);
		if (idPersonaLogeada > 0) {
			// Se actualiza el usuario
			setBanderaNuevo(false);
			personaNueva = servicioMascota.obtenerPeronaPorId(idPersonaLogeada);
			System.out.println("2 actualiza>>>>>>>>>>>>>" + personaNueva.getNombres());

		} else {
			personaNueva = new PePersona();
		}
		banderaPantalla = "GENERAL";
	}

	public String registrarUsuario() {
		System.out.println("2 actualiza>>>>>>>>>>>>>" + personaNueva.getNombres());
		banderaPantalla = "GENERAL";
		if (banderaNuevo) {
			if (personaNueva.getIdentificacion() == null || "".equals(personaNueva.getIdentificacion())) {
				agregarMensajeAdvertencia("El numero de identificación es obligatorio");
				return "";
			}
			if (personaNueva.getContrasenia() == null || "".equals(personaNueva.getContrasenia())) {
				agregarMensajeAdvertencia("La contraseña es obligatoria");
				return "";
			}
			PePersona pers = servicioMascota.obtenerPeronaPorCedula(personaNueva.getIdentificacion());
			if (pers != null) {
				agregarMensajeAdvertencia("El usuario ya se encuentra registrado");
				return "";
			}
			String claveEncriptada = util.getMD5(personaNueva.getContrasenia());
			personaNueva.setContrasenia(claveEncriptada);
			System.out.println("1 clave>>>>>>>>>>>>>" + claveEncriptada);
			String resp = servicioMascota.registrarUsuario(personaNueva);
			if ("".equals(resp)) {
				banderaPantalla = "CREADO";
				agregarMensajeInfo("Registrado con éxito");
			} else {
				agregarMensajeAdvertencia(resp);
			}
		} else {
			//String claveEncriptada = util.getMD5(personaNueva.getContrasenia());
			//personaNueva.setContrasenia(claveEncriptada);
			//System.out.println("1 clave>>>>>>>>>>>>>" + claveEncriptada);
			String resp = servicioMascota.actualizarUsuario(personaNueva);
			if ("".equals(resp)) {
				banderaPantalla = "ACTUALIZADO";
				agregarMensajeInfo("Actualizado con éxito");
			} else {
				agregarMensajeAdvertencia(resp);
			}
		}
		return "";
	}

	public String irInicio() {
		String paginaReserva = "peInicio.xhtml";
		return paginaReserva;
	}

	public PePersona getPersonaNueva() {
		return personaNueva;
	}

	public void setPersonaNueva(PePersona personaNueva) {
		this.personaNueva = personaNueva;
	}

	public String getBanderaPantalla() {
		return banderaPantalla;
	}

	public void setBanderaPantalla(String banderaPantalla) {
		this.banderaPantalla = banderaPantalla;
	}

	public boolean isBanderaNuevo() {
		return banderaNuevo;
	}

	public void setBanderaNuevo(boolean banderaNuevo) {
		this.banderaNuevo = banderaNuevo;
	}

}
