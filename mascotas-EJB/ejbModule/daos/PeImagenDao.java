package daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entidades.PeImagen;

@Stateless
public class PeImagenDao {

	@PersistenceContext
	protected EntityManager em;

	public PeImagen crear(PeImagen entity) {
		em.persist(entity);
		return entity;
	}

	public PeImagen actualizar(PeImagen entity) {
		em.merge(entity);
		return entity;
	}

	public void eliminar(PeImagen entity) throws PersistenceException {
		em.remove(entity);
	}

	public List<PeImagen> listarPeImagens() {
		try {
			String sql = "select o from PeImagen o  ";
			@SuppressWarnings("unchecked")
			List<PeImagen> lista = em.createQuery(sql).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public List<PeImagen> listarPeImagenPorId(Integer idMascota) {
		try {
			String sql = "select o from PeImagen o where o.idMascota = :idMascota ";
			@SuppressWarnings("unchecked")
			List<PeImagen> lista = em.createQuery(sql).setParameter("idMascota", idMascota).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
