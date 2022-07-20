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

@ManagedBean(name = "peRegistroController")
@SessionScoped
public class PeRegistroController extends BaseController implements Serializable {

	private static final long serialVersionUID = -1L;

	private PePersona personaNueva;

	@EJB
	private ServicioMascota servicioMascota;

	@PostConstruct
	public void inicializarComponentes() {
		personaNueva=new PePersona();
	}
	
	
	public void registrarUsuario() {
		String resp=servicioMascota.registrarUsuario(personaNueva);
		if("".equals(resp)) {
			inicializarComponentes();
			agregarMensajeInfo("Registrado con exito");
		}else {
			agregarMensajeAdvertencia(resp);
		}		
	}

	public PePersona getPersonaNueva() {
		return personaNueva;
	}

	public void setPersonaNueva(PePersona personaNueva) {
		this.personaNueva = personaNueva;
	}
	
	

}
