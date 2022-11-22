package ar.edu.unju.fi.presenter;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ar.edu.unju.fi.dao.CuentaBancariaDAO;
import ar.edu.unju.fi.dao.ClienteDAO;
import ar.edu.unju.fi.dao.impl.CuentaBancariaDAOImpl;
import ar.edu.unju.fi.dao.impl.ClienteDAOImpl;
import ar.edu.unju.fi.model.CajaAhorro;
import ar.edu.unju.fi.model.CuentaBancaria;
import ar.edu.unju.fi.model.CuentaCorriente;
import ar.edu.unju.fi.model.Cliente;
import ar.edu.unju.fi.presenter.views.IViewCuentaBancaria;
import ar.edu.unju.fi.util.ManagerContext;

public class CuentaBancariaPresenter {
	private IViewCuentaBancaria formularioAltaCuenta;
	private CuentaBancariaDAO cuentaBancariaDAO;
	private CuentaBancaria cuentaBancaria;
	private ClienteDAO clienteDAO;;
	
	public CuentaBancariaPresenter(IViewCuentaBancaria formularioAltaCuenta) {
		this.formularioAltaCuenta = formularioAltaCuenta;
		this.cuentaBancariaDAO = new CuentaBancariaDAOImpl(ManagerContext.getInstance().getEntityManager());
		this.clienteDAO = new ClienteDAOImpl(ManagerContext.getInstance().getEntityManager());
	}
	
	public void registrarCuentaCorriente(Integer idTitular, Integer numeroCuenta, Double importeComision, double saldo) {
		Cliente cliente = clienteDAO.buscarXID(idTitular);
		cuentaBancaria = new CuentaCorriente(numeroCuenta, saldo,cliente);
		cuentaBancariaDAO.guardar(cuentaBancaria);
	}

	public void cargarComboTitulares() {
		DefaultComboBoxModel myModel = new DefaultComboBoxModel();
		List<Cliente> clientes = clienteDAO.obtenerLista();
		for (Cliente cliente : clientes) {
			myModel.addElement(cliente);
		}
		formularioAltaCuenta.getComboClientes().setModel(myModel);
	}

	public void registrarCuentaBancaria(String tipoCuenta, Object selectedItem, String numeroCuenta, String saldo) {
		CuentaBancaria cuentaBancaria;
		if (tipoCuenta.equals("CAJA-AHORRO")) {
			cuentaBancaria  = new CajaAhorro(numeroCuenta, (Cliente) selectedItem, Double.parseDouble(saldo), null);
			cuentaBancariaDAO.guardar(cuentaBancaria);
		}else {
			cuentaBancaria  = new CuentaCorriente(numeroCuenta, (Cliente) selectedItem, null, Double.parseDouble(saldo));
			cuentaBancariaDAO.guardar(cuentaBancaria);
		}
		
	}
}
