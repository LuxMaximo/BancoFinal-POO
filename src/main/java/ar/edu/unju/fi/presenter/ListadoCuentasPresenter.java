package ar.edu.unju.fi.presenter;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import ar.edu.unju.fi.dao.CuentaBancariaDAO;
import ar.edu.unju.fi.dao.impl.CuentaBancariaDAOImpl;
import ar.edu.unju.fi.model.CajaAhorro;
import ar.edu.unju.fi.model.CuentaBancaria;
import ar.edu.unju.fi.presenter.views.IViewCuentaBancaria;
import ar.edu.unju.fi.util.ManagerContext;
import ar.edu.unju.fi.view.ListadoCuentasFrame;



public class ListadoCuentasPresenter {
	private IViewCuentaBancaria ventanaCuentas;
	private CuentaBancariaDAO cuentaDAO;

	public ListadoCuentasPresenter(ListadoCuentasFrame listadoCuentasFrame) {
		this.ventanaCuentas =	listadoCuentasFrame;
	}
	
	public void visualizarCuentas() {
		cuentaDAO = new CuentaBancariaDAOImpl(ManagerContext.getInstance().getEntityManager());
		List<CuentaBancaria> listadoCuentas = cuentaDAO.obtenerLista();
		DefaultTableModel model = (DefaultTableModel) ventanaCuentas.getTable().getModel();
		for(CuentaBancaria cuenta:listadoCuentas) {
			Object[] data = new Object[5];			
			data[0] = cuenta.getId();
			data[1] = cuenta.getTitular().getNombre();
			if (cuenta instanceof CajaAhorro) {
				data[2] = "CAJA AHORRO";				
			} else {
				data[2] = "CUENTA CORRIENTE";
			}
			data[3] = cuenta.getNumeroCuenta();
			data[4] = cuenta.getSaldo();
			model.addRow(data);
		}
	}
}
