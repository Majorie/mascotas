package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the pe_mascota database table.
 * 
 */
@Entity
@Table(name="pe_mascota")
public class PeMascota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_mascota")
	private Integer idMascota;

	private String detalle;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	private String nombre;
	
	private String estado;
	
	private Integer provincia;	
	private Integer raza;
	private String tamanio;
	private String color;
	private String adicional;
	

	@Transient
	private byte[] archivoImagenTmp;
	
	@Transient
	private String nombreRazaTmp;
	
	@Transient
	private String nombreProvinciaTmp;
	
	@Transient
	private String contactoReporteTmp;
	
	@Transient
	private String nombreReporteTmp;
	
	public PeMascota() {
	}

	public Integer getIdMascota() {
		return this.idMascota;
	}

	public void setIdMascota(Integer idMascota) {
		this.idMascota = idMascota;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

	public Integer getProvincia() {
		return provincia;
	}

	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}

	public Integer getRaza() {
		return raza;
	}

	public void setRaza(Integer raza) {
		this.raza = raza;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAdicional() {
		return adicional;
	}

	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}

	@Transient
	public byte[] getArchivoImagenTmp() {
		return archivoImagenTmp;
	}

	public void setArchivoImagenTmp(byte[] archivoImagenTmp) {
		this.archivoImagenTmp = archivoImagenTmp;
	}

	@Transient
	public String getNombreRazaTmp() {
		return nombreRazaTmp;
	}

	public void setNombreRazaTmp(String nombreRazaTmp) {
		this.nombreRazaTmp = nombreRazaTmp;
	}

	@Transient
	public String getNombreProvinciaTmp() {
		return nombreProvinciaTmp;
	}

	public void setNombreProvinciaTmp(String nombreProvinciaTmp) {
		this.nombreProvinciaTmp = nombreProvinciaTmp;
	}

	@Transient
	public String getContactoReporteTmp() {
		return contactoReporteTmp;
	}

	public void setContactoReporteTmp(String contactoReporteTmp) {
		this.contactoReporteTmp = contactoReporteTmp;
	}

	@Transient
	public String getNombreReporteTmp() {
		return nombreReporteTmp;
	}

	public void setNombreReporteTmp(String nombreReporteTmp) {
		this.nombreReporteTmp = nombreReporteTmp;
	}
	
	

}