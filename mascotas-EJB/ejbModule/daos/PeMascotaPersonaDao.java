package daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entidades.PeMascotaPersona;
import entidades.PePersona;

@Stateless
public class PeMascotaPersonaDao {

	@PersistenceContext
	protected EntityManager em;

	public PeMascotaPersona crear(PeMascotaPersona entity) {
		em.persist(entity);
		return entity;
	}

	public PeMascotaPersona actualizar(PeMascotaPersona entity) {
		em.merge(entity);
		return entity;
	}

	public void eliminar(PeMascotaPersona entity) throws PersistenceException {
		em.remove(entity);
	}

	public List<PeMascotaPersona> listarPeMascotaPersonas() {
		try {
			String sql = "select o from PeMascotaPersona o  ";
			@SuppressWarnings("unchecked")
			List<PeMascotaPersona> lista = em.createQuery(sql).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PeMascotaPersona listarPeMascotaPersonaPorIdPersona(String idPersona) {
		try {
			String sql = "select o from PeMascotaPersona o where o.idPersona = :idPersona ";
			@SuppressWarnings("unchecked")
			List<PeMascotaPersona> lista = em.createQuery(sql).setParameter("idPersona", idPersona).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PePersona buscarPersonaPorIdMascota(Integer idMascota) {
		try {
			String sql = "select o from PePersona o where o.idPersona = (select max(u.idPersona) from PeMascotaPersona u where u.idMascota = :idMascota ) ";
			@SuppressWarnings("unchecked")
			List<PePersona> lista = em.createQuery(sql).setParameter("idMascota", idMascota).getResultList();
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
