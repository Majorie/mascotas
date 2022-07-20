package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pe_mascota_bloq database table.
 * 
 */
@Entity
@Table(name="pe_mascota_bloq")
@NamedQuery(name="PeMascotaBloq.findAll", query="SELECT p FROM PeMascotaBloq p")
public class PeMascotaBloq implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_mascota_bloq")
	private Integer idMascotaBloq;

	private String detalle;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	@Column(name="id_mascota")
	private Integer idMascota;

	@Column(name="id_persona")
	private Integer idPersona;

	public PeMascotaBloq() {
	}

	public Integer getIdMascotaBloq() {
		return this.idMascotaBloq;
	}

	public void setIdMascotaBloq(Integer idMascotaBloq) {
		this.idMascotaBloq = idMascotaBloq;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
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