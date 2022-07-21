/**
 * 
 */
package controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 * 
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
	private boolean logeado = false;
	private boolean administrador = false;
	private String banderaGeneral;
	//private Usuario usuarioActual;
	
	//@EJB
	//private ServicioUsuario servicioUsuario;

	@PostConstruct
	public void inicializarComponentes() {
		//usuario = "admin";
		//contrasenia = "admin";
		banderaGeneral="";
		//usuarioActual=null;
		//System.out.println("Se inicializa la plantilla::::");
		mascotaInicio();
	}

	public String entrar(ActionEvent actionEvent) {
		/*
		String paginaRetorno="index.xhtml";
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		usuarioActual=null;
		usuarioActual=servicioUsuario.consultarUsuarioPorCorreoPorContrasenia(usuario, contrasenia);
		System.out.println("Se va el usuario::::"+usuarioActual);
		if(usuario != null && contrasenia != null && usuarioActual!=null && usuario.equals(usuarioActual.getCorreo()) && contrasenia.equals(usuarioActual.getContrasenia())){
			logeado = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@",
					usuarioActual.getNombres());
			System.out.println("tipo de usuaruio::"+usuarioActual.getTipo());
			if("ADMIN".equals(usuarioActual.getTipo())){
				administrador=true;
			}
			if("RESERVA".equals(banderaGeneral)){
				paginaRetorno="reserva.xhtml";
				banderaGeneral="";
			}		
		} else {
			logeado = false;
			administrador=false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error: ",
					"Credenciales no válidas");
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", logeado);
		context.addCallbackParam("usuarioActual", usuarioActual);
		if (logeado)
			context.addCallbackParam("view", "gauge.xhtml");

		System.out.println("1logeado:::" + logeado +" y va a la pag:"+paginaRetorno);
		return paginaRetorno;
		*/
		return "";
	}

	/*
	public String salir() {
		String paginaRetorno="index.xhtml";
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.invalidate();
		logeado = false;
		administrador=false;
		return paginaRetorno;
	}
*/
	
	public String paginaInicio(){
		System.out.println(">>>>viene a la pagina reserva...");
		String paginaReserva="index.xhtml";
		return paginaReserva;
	}
	
	
	
	public String logeoPePersona(){
		String paginaReserva="peLogin.xhtml";
		return paginaReserva;
	}
	
	public String registarPePersona(){
		String paginaReserva="peRegistro.xhtml";
		return paginaReserva;
	}
	
	public String reportarMascota(){
		String paginaReserva="peReportar.xhtml";
		return paginaReserva;
	}
	
	public String mascotaInicio(){
		String paginaReserva="peInicio.xhtml";
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

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	

	
}
