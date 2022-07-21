package controladores;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class BaseController implements Serializable {

	protected void agregarMensajeError(final String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));
	}

	protected void agregarMensajeInfo(final String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
	}

	protected void agregarMensajeAdvertencia(final String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, ""));
	}
	
	public boolean isExistenMensajes() {
		return FacesContext.getCurrentInstance().getMaximumSeverity() != null;
	}
}
