package ar.edu.unju.fi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ar.edu.unju.fi.dao.CuentaBancariaDAO;
import ar.edu.unju.fi.model.CuentaBancaria;

public class CuentaBancariaDAOImpl implements CuentaBancariaDAO{

	private EntityManager manager;
	
	public CuentaBancariaDAOImpl(EntityManager manager) {
		super();
		this.manager = manager;
	}
	
	@Override
	public List<CuentaBancaria> obtenerLista() {
		return manager.createQuery("from CuentaBancaria").getResultList();	
	}

	@Override
	public void guardar(CuentaBancaria cuenta) {
		manager.getTransaction().begin();
        manager.persist(cuenta);
        manager.getTransaction().commit();
	}

	@Override
	public CuentaBancaria buscarXnumeroCuenta(Integer numeroCuenta) {
		Query query = manager.createQuery("from cuentabancaria c where c.numero_cuenta = :numeroCuenta");
		query.setParameter("numero_cuenta", numeroCuenta);
		return (CuentaBancaria) query.getSingleResult();
	}

}
