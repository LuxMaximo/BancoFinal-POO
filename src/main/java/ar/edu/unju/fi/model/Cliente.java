package ar.edu.unju.fi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {
	private Integer id;
	private Long dni;
	private String nombre;
	private String email;
	private Boolean estado;
	
	
	public Cliente() {}
	
	public Cliente(Long dni, String nombre, String email) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.estado = true;
	}
	
	
	
	
	//---------Getters & Setters------
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable= false)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "dni")
	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}
	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column(name = "correo_electronico")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "estado")
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return nombre;
	}

	
	
	
}
