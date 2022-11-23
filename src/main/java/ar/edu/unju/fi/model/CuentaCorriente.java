package ar.edu.unju.fi.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CuentaCorriente extends CuentaBancaria{
	
	private Double imporComision;
	
	public CuentaCorriente() {}
	

	public CuentaCorriente(Double saldo, Cliente cliente) {
		super(saldo, cliente);
		this.imporComision = 500d;
	}


	//---------Getters & Setters------
	@Column(name ="importe_comision")
	public Double getImporComision() {
		return imporComision;
	}

	public void setImporComision(Double imporComision) {
		this.imporComision = imporComision;
	}
	
	
}
