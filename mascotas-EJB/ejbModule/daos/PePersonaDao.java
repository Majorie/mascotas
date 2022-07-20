package daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entidades.PePersona;

@Stateless
public class PePersonaDao {

	@PersistenceContext
	protected EntityManager em;

	public PePersona crear(PePersona entity) {
		em.persist(entity);
		return entity;
	}

	public PePersona actualizar(PePersona entity) {
		try {
			em.merge(entity);
			return entity;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}

	public void eliminar(PePersona entity) throws PersistenceException {
		em.remove(entity);
	}

	public List<PePersona> listarPePersonas() {
		try {
			String sql = "select o from PePersona o  ";
			@SuppressWarnings("unchecked")
			List<PePersona> lista = em.createQuery(sql).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
		
	public PePersona consultarPePersonaPorCedula(String cedula) {
		try {
			String sql = "select o from PePersona o where o.identificacion = :cedula ";
			@SuppressWarnings("unchecked")
			List<PePersona> lista = em.createQuery(sql)
					.setParameter("cedula", cedula).getResultList();
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
