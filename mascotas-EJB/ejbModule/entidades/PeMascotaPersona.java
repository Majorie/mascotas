package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pe_mascota_persona database table.
 * 
 */
@Entity
@Table(name="pe_mascota_persona")
@NamedQuery(name="PeMascotaPersona.findAll", query="SELECT p FROM PeMascotaPersona p")
public class PeMascotaPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_mascota_persona")
	private Integer idMascotaPersona;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_perdida")
	private Date fechaPerdida;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_recupera")
	private Date fechaRecupera;

	@Column(name="id_mascota")
	private Integer idMascota;

	@Column(name="id_persona")
	private Integer idPersona;

	public PeMascotaPersona() {
	}

	public Integer getIdMascotaPersona() {
		return this.idMascotaPersona;
	}

	public void setIdMascotaPersona(Integer idMascotaPersona) {
		this.idMascotaPersona = idMascotaPersona;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaPerdida() {
		return this.fechaPerdida;
	}

	public void setFechaPerdida(Date fechaPerdida) {
		this.fechaPerdida = fechaPerdida;
	}

	public Date getFechaRecupera() {
		return this.fechaRecupera;
	}

	public void setFechaRecupera(Date fechaRecupera) {
		this.fechaRecupera = fechaRecupera;
	}

	public Integer getIdMascota() {
		return this.idMascota;
	}

	public void setIdMascota(Integer idMascota) {
		this.idMascota = idMascota;
	}

	public Integer getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

}