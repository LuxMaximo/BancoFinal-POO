package ar.edu.unju.fi.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CajaAhorro extends CuentaBancaria{

	private Double limite;

	//Constructor por defecto
	public CajaAhorro() {}
	
	//Constructor con atributos
	public CajaAhorro(Cliente cliente, Double saldo, Integer num) {
		super( saldo, cliente, num);
		this.limite = 2000d;
	}

	//---------Getters & Setters------
	@Column(name = "limite_extraccion")
	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}
	
	
}
