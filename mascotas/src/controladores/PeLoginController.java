/**
 * 
 */
package controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import entidades.PePersona;
import servicios.ServicioMascota;

/**
 * 
 * @author MTorres
 *
 */

@ManagedBean(name = "peLoginController")
@SessionScoped
public class PeLoginController extends BaseController implements Serializable {

	private static final long serialVersionUID = -1L;

	private String identificacion;
	private String contrasenia;
	private PePersona persona;

	@EJB
	private ServicioMascota servicioMascota;

	@PostConstruct
	public void inicializarComponentes() {
		persona=new PePersona();
	}
	
	public void ingresar() {
		
	}
	
	public String registrase() {
		String paginaReserva="peRegistro.xhtml";
		return paginaReserva;
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
	
	

}
