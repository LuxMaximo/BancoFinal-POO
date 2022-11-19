package ar.edu.unju.fi.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import ar.edu.unju.fi.dao.UsuarioDAO;
import ar.edu.unju.fi.dao.impl.UsuarioDAOImpl;
import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.util.ManagerContext;

public class UsuarioTest {
	ManagerContext context;
	EntityManager entityManager; 
	UsuarioDAO usuarioDAO;
	Usuario usuario;
	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Configurando en SetUp...");
		context = ManagerContext.getInstance();
	    entityManager = context.getEntityManager();
		usuarioDAO = new UsuarioDAOImpl(entityManager);
		usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setPassword("123");
				
	}
	
	//@Disabled
	@Test
	@DisplayName("Prueba Agregar Usuario")
	void altaUsuarioTest() {
		List<Usuario> usuarios = usuarioDAO.obtenerLista();
		int size = usuarios.size();
		usuarioDAO.guardar(usuario);
		usuarios = usuarioDAO.obtenerLista();		
		size += 1;
		assertEquals(size, usuarios.size());
	}
	

	@Test
	@DisplayName("Prueba login Usuario")
	void loginUsuarioTest() {
		String login = "admin";
		String password = "123";	
		Usuario usuario = usuarioDAO.buscarXLoginYPassword(login, password);
		assertNotNull(usuario);
	}
	
}