package ar.edu.unju.fi.presenter;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

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
	
	public void obtenerLista() {
		cuentaBancariaDAO.obtenerLista();
	}

	public void cargarComboTitulares() {
		DefaultComboBoxModel myModel = new DefaultComboBoxModel();
		List<Cliente> clientes = clienteDAO.obtenerLista();
		for (Cliente cliente : clientes) {
			myModel.addElement(cliente);
		}
		formularioAltaCuenta.getComboClientes().setModel(myModel);
	}

	public void registrarCuentaBancaria(String tipoCuenta, Cliente selectedItem, String saldo) {
		CuentaBancaria cuentaBancaria;
		Cliente cliente = clienteDAO.buscarXID(selectedItem.getId());
		Boolean bandera=false;
		List <CuentaBancaria> ls = cuentaBancariaDAO.obtenerLista();
		
		//verifico si el cliente ya tiene una cuenta bancaria
		for (CuentaBancaria listacuentas : ls) {
			if (listacuentas.getCliente().getId() == cliente.getId()) {
				bandera = true;
				JOptionPane.showMessageDialog(null, "Este cliente ya tiene una cuenta bancaria");
			}
		}
		
		
		//Verifica que tipo de cuenta eligio
		if (tipoCuenta.equals("CAJA-AHORRO")) {
			cuentaBancaria  = new CajaAhorro( (Cliente) selectedItem, Double.parseDouble(saldo));
			if(!bandera) {
				cuentaBancariaDAO.guardar(cuentaBancaria);
			}
		}else {
			cuentaBancaria  = new CuentaCorriente( Double.parseDouble(saldo),(Cliente) selectedItem);
			if(!bandera) {
				cuentaBancariaDAO.guardar(cuentaBancaria);
			}
		}
		
	}
}
