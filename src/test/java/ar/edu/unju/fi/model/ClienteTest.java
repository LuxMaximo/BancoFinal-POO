package ar.edu.unju.fi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ar.edu.unju.fi.dao.ClienteDAO;
import ar.edu.unju.fi.dao.impl.ClienteDAOImpl;
import ar.edu.unju.fi.util.ManagerContext;


public class ClienteTest {
	ManagerContext context;
	EntityManager entityManager; 
	ClienteDAO clienteDAO;
	Cliente cliente1;
	Cliente cliente2;

	@BeforeEach
	void setUp() throws Exception {
		context = ManagerContext.getInstance();
	    entityManager = context.getEntityManager();
		clienteDAO = new ClienteDAOImpl(entityManager);
		cliente1 = new Cliente(4455664l, "Maxi", "correoverdador@hola.com");
		cliente2 = new Cliente(12324123l, "Lucas", "sadasd@asdasd");
	}
	
	@Test
	@DisplayName("Prueba Agregar un Cliente")
	void altaClienteTest() {
		List<Cliente> clientes = clienteDAO.obtenerLista();
		int size = clientes.size();
		clienteDAO.guardar(cliente1);
		clienteDAO.guardar(cliente2);
		clientes = clienteDAO.obtenerLista();		
		for (Cliente cli : clientes) {
			System.out.println("nombre -->" + cli.getNombre());
		}
		size += 2;
		assertEquals(size, clientes.size());
	}

}