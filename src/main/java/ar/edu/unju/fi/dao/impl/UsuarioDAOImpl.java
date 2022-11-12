package ar.edu.unju.fi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ar.edu.unju.fi.dao.UsuarioDAO;
import ar.edu.unju.fi.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO{

	private EntityManager manager;
	
	public UsuarioDAOImpl(EntityManager manager) {
		super();
		this.manager = manager;
	}
	
	@Override
	public List<Usuario> obtenerLista() {
		return manager.createQuery("from Usuario").getResultList();
	}

	@Override
	public void guardar(Usuario usuario) {
		manager.getTransaction().begin();
	    manager.persist(usuario);
	    manager.getTransaction().commit();	
	}

	@Override
	public Usuario buscarXLoginYPassword(String login, String password) {
		Query query = manager.createQuery("from Usuario u where u.login=:login and u.password=:password");
		query.setParameter("login", login);
		query.setParameter("password", password);
		try {
			return (Usuario) query.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}	
	}

}
