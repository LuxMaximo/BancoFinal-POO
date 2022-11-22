package ar.edu.unju.fi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import ar.edu.unju.fi.dao.ClienteDAO;
import ar.edu.unju.fi.model.Cliente;


public class ClienteDAOImpl implements ClienteDAO{

	private EntityManager manager;
	private EntityManagerFactory factory;
	
	
	public ClienteDAOImpl() {
		super();
	}

	public ClienteDAOImpl(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public List<Cliente> obtenerLista() {
		return manager.createQuery("from Cliente").getResultList();
	}
	
	@Override
	public void guardar(Cliente cliente) {
        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.getTransaction().commit();	
		
	}
	
	@Override
	public List<Cliente> buscarXNombre(String nombre) {
		Query query =  manager.createQuery("from Cliente t where t.nombre like concat('%', :nombre, '%')");
		query.setParameter("nombre", nombre);
		return query.getResultList();
	}
	
	@Override
	public Cliente buscarXID(Integer idCliente) {
		Query query =  manager.createQuery("from Cliente t where t.id=:idCliente");
		query.setParameter("idCliente", idCliente);
		return (Cliente) query.getSingleResult();
	}
	
	@Override
	public void actualizar(Cliente clienteModel) {
        manager.getTransaction().begin();
        manager.merge(clienteModel);
        manager.getTransaction().commit();	
	}
	
	

}
