package ar.edu.unju.fi.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CajaAhorro extends CuentaBancaria{

	private Double limite;

	//Constructor por defecto
	public CajaAhorro() {}
	
	//Constructor con atributos
	public CajaAhorro(Integer numCuenta,Cliente cliente, Double saldo) {
		super(numCuenta, saldo, cliente);
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
	
	@Override
	public Double depositar(Double importe) {
		return this.saldo = this.saldo + importe;
	}
	
	@Override
	public Double extraer(Double importe) {
		if(this.saldo >= importe) {
			this.saldo = this.saldo - importe;
		}
		return this.saldo;
	}
	
	
}
