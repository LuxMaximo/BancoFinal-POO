package ar.edu.unju.fi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ar.edu.unju.fi.dao.CuentaBancariaDAO;
import ar.edu.unju.fi.model.CuentaBancaria;
import ar.edu.unju.fi.model.Usuario;

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
		Query query = manager.createQuery("from CuentaBancaria c where c.numCuenta = :numeroCuenta");
		query.setParameter("numeroCuenta", numeroCuenta);
		return (CuentaBancaria) query.getSingleResult();
	}

	@Override
	public void depositar(CuentaBancaria cuentaImporte) {
		manager.getTransaction().begin();
        manager.merge(cuentaImporte);
        manager.getTransaction().commit();
	}
	
	@Override
	public void extraer(CuentaBancaria cuentaExtraccion) {
		// TODO Auto-generated method stub
		manager.getTransaction().begin();
        manager.merge(cuentaExtraccion);
        manager.getTransaction().commit();
	}
	
	@Override
	public CuentaBancaria buscarXTipo(String tipoCuenta) {
		Query query = manager.createQuery("from CuentaBancaria c where c.DTYPE = :tipoCuenta");
		query.setParameter("DTYPE", tipoCuenta);
		return (CuentaBancaria) query.getSingleResult();	
	}
}
