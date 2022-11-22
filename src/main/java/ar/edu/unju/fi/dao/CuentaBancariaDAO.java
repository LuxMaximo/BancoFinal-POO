package ar.edu.unju.fi.dao;

import java.util.List;

import ar.edu.unju.fi.model.CuentaBancaria;


public interface CuentaBancariaDAO {

	List<CuentaBancaria> obtenerLista();
	
	void guardar(CuentaBancaria cuenta);

	CuentaBancaria buscarXnumeroCuenta(Integer numeroCuenta);
	
	void depositar (CuentaBancaria cuentaImporte);
	
	void extraer(CuentaBancaria cuentaExtraccion);
	
	CuentaBancaria buscarXTipo(String tipoCuenta);
	
}
