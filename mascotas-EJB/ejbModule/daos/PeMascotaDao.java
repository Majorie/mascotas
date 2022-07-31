package daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entidades.PeMascota;
import entidades.PeProvincia;
import entidades.PeRaza;

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

	public List<PeMascota> listarPeMascotasPorTipo(String tipo) {
		try {
			String sql = "select o from PeMascota o where o.estado = :tipo  ";
			@SuppressWarnings("unchecked")
			List<PeMascota> lista = em.createQuery(sql).setParameter("tipo", tipo).getResultList();
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

	public List<PeMascota> listarPeMascotasPorFiltro(String filtro) {
		try {
			System.out.println("1 valorBuscar>>>" + filtro);
			String sql = "select o from PeMascota o where UPPER(o.color) like :filtro or UPPER(o.tamanio) like :filtro or o.raza in (select r.idRaza from PeRaza r where r.nombre like :filtro ) or o.provincia in (select p.idProvincia from PeProvincia p where p.nombre like :filtro ) ";
			System.out.println("2 valorBuscar>>>" + sql);
			@SuppressWarnings("unchecked")
			List<PeMascota> lista = em.createQuery(sql).setParameter("filtro", "%" + filtro.toUpperCase() + "%").getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			System.out.println("3 valorBuscar>>>" + lista.size());
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<PeProvincia> listarProvincias() {
		try {
			String sql = "select o from PeProvincia o ";
			@SuppressWarnings("unchecked")
			List<PeProvincia> lista = em.createQuery(sql).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PeProvincia buscarProvinciPorId(Integer id) {
		try {
			String sql = "select o from PeProvincia o where o.idProvincia = :id ";
			@SuppressWarnings("unchecked")
			List<PeProvincia> lista = em.createQuery(sql).setParameter("id", id).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<PeRaza> listarRazas() {
		try {
			String sql = "select o from PeRaza o ";
			@SuppressWarnings("unchecked")
			List<PeRaza> lista = em.createQuery(sql).getResultList();
			if (lista.isEmpty()) {
				return null;
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PeRaza buscarRazaPorId(Integer id) {
		try {
			String sql = "select o from PeRaza o where o.idRaza = :id ";
			@SuppressWarnings("unchecked")
			List<PeRaza> lista = em.createQuery(sql).setParameter("id", id).getResultList();
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
