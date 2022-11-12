package ar.edu.unju.fi.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class CuentaBancaria {
	private Long id;
	private Integer numCuenta;
	private Date fechaCreacion;
	private Double saldo;
	private Cliente cliente;
	
	 public CuentaBancaria() {}
	
	public CuentaBancaria(Integer numCuenta, Double saldo, Cliente cliente) {
		super();
		this.numCuenta = numCuenta;
		this.fechaCreacion = new Date();
		this.saldo = saldo;
		this.cliente = cliente;
	}


	//---------Getters & Setters------
	@Column(name = "numero_cuenta")
	public Integer getNumCuenta() {
		return numCuenta;
	}



	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id", nullable = false)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable= false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public void setNumCuenta(Integer numCuenta) {
		this.numCuenta = numCuenta;
	}


	@Column(name = "fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	@Column(name = "saldo")
	public Double getSaldo() {
		return saldo;
	}



	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
	
	
}
