package ar.edu.unju.fi.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ar.edu.unju.fi.dao.ClienteDAO;
import ar.edu.unju.fi.dao.CuentaBancariaDAO;
import ar.edu.unju.fi.dao.impl.ClienteDAOImpl;
import ar.edu.unju.fi.dao.impl.CuentaBancariaDAOImpl;
import ar.edu.unju.fi.model.CajaAhorro;
import ar.edu.unju.fi.model.Cliente;
import ar.edu.unju.fi.model.CuentaBancaria;
import ar.edu.unju.fi.model.CuentaCorriente;
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
	
	
	@Test
	@DisplayName("Prueba realizar extraccion")
	@Order(3)
	void realizarExtraccion() {
		Integer numeroCuenta = 1;
		CuentaBancaria cuenta = cuentaBancariaDAO.buscarXnumeroCuenta(numeroCuenta);
		System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());
		float importeExtraccion = 500;
		if(cuenta instanceof CajaAhorro) {
			Double limite = ((CajaAhorro) cuenta).getLimite();
			if(importeExtraccion <= limite) {
				cuenta.setSaldo(cuenta.getSaldo() - importeExtraccion);
				cuentaBancariaDAO.extraer(cuenta);
				assertEquals(500,cuenta.getSaldo());
			}			
		}else {
			if(cuenta instanceof CuentaCorriente) {
				Double comision = ((CuentaCorriente) cuenta).getImporComision();
				cuenta.setSaldo((cuenta.getSaldo() - importeExtraccion) + comision);
				cuentaBancariaDAO.extraer(cuenta);
				assertEquals(1000,cuenta.getSaldo());
			}
		}
	}
	
	@Test
	@DisplayName("Prueba realizar deposito")
	@Order(4)
	void realizarDeposito() {
		System.out.println("Comienza...");
		
		Integer numCuenta = 1;
		Double importeDeposito = 50d;
		CuentaBancaria cuenta = cuentaBancariaDAO.buscarXnumeroCuenta(numCuenta);
		System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());
		if(cuenta instanceof CajaAhorro) {
			cuenta.setSaldo(cuenta.getSaldo() + importeDeposito);
			cuentaBancariaDAO.depositar(cuenta);
			assertEquals(550d,cuenta.getSaldo());
		}else {
			if(cuenta instanceof CuentaCorriente) {
				cuenta.setSaldo(cuenta.getSaldo() + importeDeposito);
				cuentaBancariaDAO.depositar(cuenta);
				assertEquals(2050d,cuenta.getSaldo());
			}
		}
	}
}
