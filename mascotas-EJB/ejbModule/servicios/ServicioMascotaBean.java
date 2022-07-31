package servicios;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import daos.PeImagenDao;
import daos.PeMascotaBloqDao;
import daos.PeMascotaDao;
import daos.PeMascotaPersonaDao;
import daos.PePersonaDao;
import entidades.PeImagen;
import entidades.PeMascota;
import entidades.PeMascotaBloq;
import entidades.PeMascotaPersona;
import entidades.PePersona;
import entidades.PeProvincia;
import entidades.PeRaza;

@Stateless
public class ServicioMascotaBean implements ServicioMascota {

	@EJB
	private PeMascotaDao mascotaDao;

	@EJB
	private PePersonaDao personaDao;

	@EJB
	private PeMascotaBloqDao mascotaBloqDao;

	@EJB
	private PeImagenDao imagenDao;

	@EJB
	private PeMascotaPersonaDao mascotaPersonaDao;
	
	
	public String guardarImagen(PeImagen imagen) {
		try {
			imagenDao.crear(imagen);
			return "";
		}catch (Exception e) {
			e.printStackTrace();
			return "error al guardar la imagen";
		}		
	}
	
	public List<PeImagen> listarImagenesPorIdMascota(Integer idMascota) {
		return imagenDao.listarPeImagenPorId(idMascota);
	}

	public List<PeMascota> listarPeMascotasPorIdPersona(Integer idPersona) {
		return mascotaDao.listarPeMascotasPorIdPersona(idPersona);
	}
	
	public List<PeMascota> listarPeMascotasPorTipo(String tipo) {
		return mascotaDao.listarPeMascotasPorTipo(tipo);
	}
	
	public List<PeMascota> listarPeMascotasPorFiltro(String filtro) {
		return mascotaDao.listarPeMascotasPorFiltro(filtro);
	}
	
	public List<PeMascota> listarPeMascotas() {
		return mascotaDao.listarPeMascotas();
	}
	
	public PePersona obtenerPeronaPorUsuarioCOntrasenia(String usuraio, String contrasenia) {
		return personaDao.consultarPePersonaPorCedulaPorCOntrasenia(usuraio, contrasenia);
	}
	
	public PePersona obtenerPeronaPorId(Integer idPersona) {
		return personaDao.consultarPePersonaPorId(idPersona);
	}
	
	public PePersona obtenerPeronaReportaPorIdMascota(Integer idMascota) {
		return mascotaPersonaDao.buscarPersonaPorIdMascota(idMascota);
	}
	
	public PePersona obtenerPeronaPorCedula(String cedula) {
		return personaDao.consultarPePersonaPorCedula(cedula);
	}
	
	public String registrarUsuario(PePersona persona) {
		try {
			personaDao.crear(persona);
			return "";
		}catch (Exception e) {
			e.printStackTrace();
			return "Error al guardar el registro";
		}
	}
	
	public String actualizarUsuario(PePersona persona) {
		try {
			personaDao.actualizar(persona);
			return "";
		}catch (Exception e) {
			e.printStackTrace();
			return "Error al actualizar el registro";
		}
	}
	
	public String crearMascota(PeMascota mascota, Integer idPersona) {
		try {
			PeMascota mascotaNueva=mascotaDao.crear(mascota);
			if(mascotaNueva==null) {	
				return "Error al guardar mascota";
			}else {
				PeMascotaPersona mascotaPer=new PeMascotaPersona();
				mascotaPer.setEstado("ACTIVO");
				mascotaPer.setFechaIngreso(new Date());
				mascotaPer.setFechaPerdida(mascotaNueva.getFechaIngreso());
				mascotaPer.setIdMascota(mascotaNueva.getIdMascota());
				mascotaPer.setIdPersona(idPersona);
				mascotaPersonaDao.crear(mascotaPer);
				return "";
			}			
		}catch (Exception e) {
			e.printStackTrace();
			return "Error al guardar el registro";
		}		
	}	
	
	public String actualizarMascota(PeMascota mascota) {
		try {
				mascotaDao.actualizar(mascota);
				return "";
		}catch (Exception e) {
			e.printStackTrace();
			return "Error al actualizar el registro";
		}		
	}	
	
	public List<PeMascotaBloq> listarPemascotaPorIdMascota(Integer idMascota){
		return  mascotaBloqDao.consultarPeMascotaBloqPorIdMascota(idMascota) ;
	}
	
	public String guardarMascotaBloq(PeMascotaBloq mascotaBloq){
		try {
			mascotaBloqDao.crear(mascotaBloq);
			return "";
		}catch (Exception e) {
			e.printStackTrace();
			return "Error al comentar";
		}
	}
	
	public List<PeProvincia> listarProvincias() {
		return mascotaDao.listarProvincias();
	}
	
	public PeProvincia buscarProvinciPorId(Integer id) {
		return mascotaDao.buscarProvinciPorId(id);
	}
	
	public List<PeRaza> listarRazas() {
		return mascotaDao.listarRazas();
	}
	
	public PeRaza buscarRazaPorId(Integer id) {
		return mascotaDao.buscarRazaPorId(id);
	}
	
}
