package daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entidades.PeMascota;

@Stateless
public class PeMascotaDao {

	@PersistenceContext
	protected EntityManager em;

	public PeMascota crear(PeMascota entity) {
		em.persist(entity);
		return entity;
	}

	public PeMascota actualizar(PeMascota entity) {
		em.merge(entity);
		return entity;
	}

	public void eliminar(PeMascota entity) throws PersistenceException {
		em.remove(entity);
	}

	public List<PeMascota> listarPeMascotas() {
		try {
			String sql = "select o from PeMascota o  ";
			@SuppressWarnings("unchecked")
			List<PeMascota> lista = em.createQuery(sql).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<PeMascota> listarPeMascotasPorIdPersona(Integer idPersona) {
		try {
			String sql = "select o from PeMascota o where o.idMascota in (select u.idMascota from PeMascotaPersona u where u.idPersona = :idPersona ) ";
			@SuppressWarnings("unchecked")
			List<PeMascota> lista = em.createQuery(sql).setParameter("idPersona", idPersona).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public PeMascota listarPeMascotaPorId(String idMascota) {
		try {
			String sql = "select o from PeMascota o where o.idMascota = :idMascota ";
			@SuppressWarnings("unchecked")
			List<PeMascota> lista = em.createQuery(sql).setParameter("idMascota", idMascota).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
