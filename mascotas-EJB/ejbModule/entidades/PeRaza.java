package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the pe_imagen database table.
 * 
 */
@Entity
@Table(name="pe_raza")
public class PeRaza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_raza")
	private Integer idRaza;

	private String nombre;

	public PeRaza() {
	}

	

	public Integer getIdRaza() {
		return idRaza;
	}



	public void setIdRaza(Integer idRaza) {
		this.idRaza = idRaza;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}