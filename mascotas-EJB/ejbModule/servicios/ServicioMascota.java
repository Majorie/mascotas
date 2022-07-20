package servicios;


import java.util.List;

import javax.ejb.Local;

import entidades.PeImagen;
import entidades.PeMascota;
import entidades.PePersona;

@Local
public interface ServicioMascota {

	public String guardarImagen(PeImagen imagen) ;
	
	public List<PeImagen> listarImagenesPorIdMascota(Integer idMascota);
	
	public List<PeMascota> listarPeMascotas();
	
	public List<PeMascota> listarPeMascotasPorIdPersona(Integer idPersona);
	
	public String registrarUsuario(PePersona persona);
	
	public String crearMascota(PeMascota mascota, Integer idPersona);
	
}
