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
import entidades.PeMascotaPersona;
import entidades.PePersona;

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
	
	public List<PeMascota> listarPeMascotas() {
		return mascotaDao.listarPeMascotas();
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
	
	public String crearMascota(PeMascota mascota, Integer idPersona) {
		try {
			System.out.println("viene a guardar1:::");
			PeMascota mascotaNueva=mascotaDao.crear(mascota);
			System.out.println("viene a guardar2:::");
			if(mascotaNueva==null) {	
				System.out.println("viene a guardar3:::");
				return "Error al guardar mascota";
			}else {
				System.out.println("viene a guardar4:::");
				PeMascotaPersona mascotaPer=new PeMascotaPersona();
				mascotaPer.setEstado("ACTIVO");
				mascotaPer.setFechaIngreso(new Date());
				mascotaPer.setFechaPerdida(mascotaNueva.getFechaIngreso());
				mascotaPer.setIdMascota(mascotaNueva.getIdMascota());
				mascotaPer.setIdPersona(idPersona);
				mascotaPersonaDao.crear(mascotaPer);
				System.out.println("viene a guardar5:::");
				return "";
			}			
		}catch (Exception e) {
			e.printStackTrace();
			return "Error al guardar el registro :crearMascota:";
		}		
	}	
	
}
