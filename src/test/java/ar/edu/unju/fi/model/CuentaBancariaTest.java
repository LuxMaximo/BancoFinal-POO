package ar.edu.unju.fi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ar.edu.unju.fi.dao.ClienteDAO;
import ar.edu.unju.fi.dao.CuentaBancariaDAO;
import ar.edu.unju.fi.dao.impl.ClienteDAOImpl;
import ar.edu.unju.fi.dao.impl.CuentaBancariaDAOImpl;
import ar.edu.unju.fi.util.ManagerContext;

@DisplayName("Pruebas unitarias cuentas bancarias JPA")
public class CuentaBancariaTest {
	
	ManagerContext context;
	EntityManager entityManager;
	CuentaBancariaDAO cuentaBancariaDAO;
	ClienteDAO clienteDAO;
	Cliente cliente1;
	Cliente cliente2;
	
	@BeforeEach
	void setUp() throws Exception{
		context = ManagerContext.getInstance();
	    entityManager = context.getEntityManager();
	    cuentaBancariaDAO = new CuentaBancariaDAOImpl(entityManager);
	    clienteDAO = new ClienteDAOImpl(entityManager);
		cliente1 = new Cliente(4455664l, "Maxi", "correoverdador@hola.com");
		cliente2 = new Cliente(12324123l, "Lucas", "sadasd@asdasd");
	}
	
	@Test
	@DisplayName("Prueba Agregar cuenta Caja Ahorro")
	@Order(1)
	void altaCajaAhorroTest() {		
		clienteDAO.guardar(cliente1);
		CuentaBancaria cuenta = new CajaAhorro(1, cliente1, 1000d);
		cuentaBancariaDAO.guardar(cuenta);
		List<CuentaBancaria> cuentas = cuentaBancariaDAO.obtenerLista();
		assertEquals(1, cuentas.size());
	}
	
	@Test
	@DisplayName("Prueba Agregar cuenta Corriente")
	@Order(2)
	void altaCuentaCorrienteTest() {		
		clienteDAO.guardar(cliente2);
		CuentaBancaria cuenta = new CuentaCorriente(2, 2000d, cliente2);
		cuentaBancariaDAO.guardar(cuenta);
		List<CuentaBancaria> cuentas = cuentaBancariaDAO.obtenerLista();
		assertEquals(2, cuentas.size());
	}
	
}
