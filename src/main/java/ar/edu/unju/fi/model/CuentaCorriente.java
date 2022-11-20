package ar.edu.unju.fi.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CuentaCorriente extends CuentaBancaria{
	
	private Double imporComision;
	
	public CuentaCorriente() {}
	

	public CuentaCorriente(Integer numCuenta, Double saldo, Cliente cliente) {
		super(numCuenta, saldo, cliente);
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
