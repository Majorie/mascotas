/**
 *
 */
package controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import entidades.PePersona;
import servicios.ServicioMascota;

/**
 * pasntalla de logeo para ingreso de usuarios a la aplicación
 * @author casilva2
 *
 */

@ManagedBean(name = "peLoginController")
@ViewScoped
public class PeLoginController extends BaseController implements Serializable {

	private static final long serialVersionUID = -1L;

	private String identificacion;
	private String contrasenia;
	private PePersona persona;
	private boolean logeado = false;

	@EJB
	private ServicioMascota servicioMascota;

	@PostConstruct
	public void inicializarComponentes() {
		persona = new PePersona();
	}

	public void ingresar() {

	}

	public String registrarse() {
		String paginaReserva = "peRegistro.xhtml";
		return paginaReserva;
	}

	public String login() {
		String pant="";
		if (identificacion != null && contrasenia != null) {
			logeado = true;
			agregarMensajeInfo("Bienvenido/a");
		} else {
			logeado = false;
			agregarMensajeAdvertencia("Credenciales no válidas");
		}	
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("estaLogeado", logeado);
		if (logeado) {			
			pant= "peInicio.xhtml";
		}			
		return pant;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public PePersona getPersona() {
		return persona;
	}

	public void setPersona(PePersona persona) {
		this.persona = persona;
	}

	public boolean isLogeado() {
		return logeado;
	}

	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}

}
