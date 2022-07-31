package servicios;


import java.util.List;

import javax.ejb.Local;

import entidades.PeImagen;
import entidades.PeMascota;
import entidades.PeMascotaBloq;
import entidades.PePersona;
import entidades.PeProvincia;
import entidades.PeRaza;

/**
 * 
 * @author MTorres
 *
 */
@Local
public interface ServicioMascota {

	/**
	 * Permite guardar la imagen relacionada a la mascota
	 * @param imagen Objeto imagen
	 * @return Devuelve un error en caso de existir error
	 */
	public String guardarImagen(PeImagen imagen) ;
	
	/**
	 * Obtiene el listado de imágenes relacionadas a una mascota, de acuerdo al identificador
	 * @param idMascota
	 * @return Lista de imágenes
	 */
	public List<PeImagen> listarImagenesPorIdMascota(Integer idMascota) ;
	
	/**
	 * Obtiene la lista de mascotas registradas
	 * @return Lista de mascotas
	 */
	public List<PeMascota> listarPeMascotas();
	
	/**
	 * Obtiene la lista de mascotas, de acuerdo al identificador de la persona que ha reportado
	 * @param idPersona
	 * @return Lista de mascotas
	 */
	public List<PeMascota> listarPeMascotasPorIdPersona(Integer idPersona);
	
	/**
	 * Obtiene la lista de mascotas filtradas por tipo (Extraviadas, Encontradas y Recuperadas)
	 * @param tipo
	 * @return Lista de mascotas
	 */
	public List<PeMascota> listarPeMascotasPorTipo(String tipo);
	
	/**
	 * Obtiene la lista de mascotas filtradas por el campo color, provincia o raza
	 * @param filtro
	 * @return Lista de mascotas
	 */
	public List<PeMascota> listarPeMascotasPorFiltro(String filtro) ;
	
	/**
	 * Obtiene una persona de acuerdo a su identificación
	 * @param cedula
	 * @return Persona
	 */
	public PePersona obtenerPeronaPorCedula(String cedula);
	
	/**
	 * Obtiene una persona de acuerdo a su identificador (id)
	 * @param idPersona
	 * @return persona
	 */
	public PePersona obtenerPeronaPorId(Integer idPersona);
	
	/**
	 * Obtiene una persona de acuerdo al id de la mascota reportada
	 * @param idMascota
	 * @return Persona
	 */
	public PePersona obtenerPeronaReportaPorIdMascota(Integer idMascota);
	
	/**
	 * Permite registrar un nuevo usuario en el sistema
	 * @param persona
	 * @return 
	 */
	public String registrarUsuario(PePersona persona);
	
	/**
	 * Permite actualizar el usuario
	 * @param persona
	 * @return
	 */
	public String actualizarUsuario(PePersona persona);
	
	/**
	 * Permite buscar un usuario, de acuerdo a las credenciales ingresadas
	 * @param usuraio
	 * @param contrasenia
	 * @return Persona
	 */
	public PePersona obtenerPeronaPorUsuarioCOntrasenia(String usuraio, String contrasenia);
	
	/**
	 * Permite registrar la mascota reportada
	 * @param mascota
	 * @param idPersona
	 * @return
	 */
	public String crearMascota(PeMascota mascota, Integer idPersona);
	
	/**
	 * Permite actualizar una mascota registrada
	 * @param mascota
	 * @return
	 */
	public String actualizarMascota(PeMascota mascota);
	
	/**
	 * Permite obtener los detalles de reporte de las mascotas
	 * @param idMascota
	 * @return Lista de detalles
	 */
	public List<PeMascotaBloq> listarPemascotaPorIdMascota(Integer idMascota);
	
	/**
	 * Permite registrar los detalles de reporte de las mascotas
	 * @param mascotaBloq
	 * @return
	 */
	public String guardarMascotaBloq(PeMascotaBloq mascotaBloq);
	
	/**
	 * Permite obtener el catálogo de provincias
	 * @return Lista de provincias
	 */
	public List<PeProvincia> listarProvincias() ;
	
	/**
	 * Permite obtener una provincia de acuerdo a su id
	 * @param id
	 * @return Provincia
	 */
	public PeProvincia buscarProvinciPorId(Integer id) ;
	
	/**
	 * Permite obtener la lista de razas registradas
	 * @return Lista de razas
	 */
	public List<PeRaza> listarRazas() ;
	
	/**
	 * Permite obtener una raza de acuerdo a su id
	 * @param id
	 * @return Raza
	 */
	public PeRaza buscarRazaPorId(Integer id);
}
