package daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entidades.PeMascotaBloq;

@Stateless
public class PeMascotaBloqDao {

	@PersistenceContext
	protected EntityManager em;

	public PeMascotaBloq crear(PeMascotaBloq entity) {
		em.persist(entity);
		return entity;
	}

	public PeMascotaBloq actualizar(PeMascotaBloq entity) {
		try {
			em.merge(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void eliminar(PeMascotaBloq entity) throws PersistenceException {
		em.remove(entity);
	}

	public List<PeMascotaBloq> listarPeMascotaBloqs() {
		try {
			String sql = "select o from PeMascotaBloq o  ";
			@SuppressWarnings("unchecked")
			List<PeMascotaBloq> lista = em.createQuery(sql).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PeMascotaBloq consultarPeMascotaBloqPorIdMascotaPorIdPersona(Integer idMascota, Integer idPersona) {
		try {
			String sql = "select o from PeMascotaBloq o where o.idMascota = :idMascota and o.idPersona = :idPersona ";
			@SuppressWarnings("unchecked")
			List<PeMascotaBloq> lista = em.createQuery(sql).setParameter("idMascota", idMascota)
					.setParameter("idPersona", idPersona).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<PeMascotaBloq> consultarPeMascotaBloqPorIdMascota(Integer idMascota) {
		try {
			String sql = "select o from PeMascotaBloq o where o.idMascota = :idMascota ";
			@SuppressWarnings("unchecked")
			List<PeMascotaBloq> lista = em.createQuery(sql).setParameter("idMascota", idMascota).getResultList();
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
