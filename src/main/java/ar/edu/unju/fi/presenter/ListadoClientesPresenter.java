package ar.edu.unju.fi.presenter;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import ar.edu.unju.fi.dao.ClienteDAO;
import ar.edu.unju.fi.dao.impl.ClienteDAOImpl;
import ar.edu.unju.fi.model.Cliente;
import ar.edu.unju.fi.presenter.views.IViewClientes;
import ar.edu.unju.fi.util.ManagerContext;

public class ListadoClientesPresenter {
	private IViewClientes ventanaClientes;
	private ClienteDAO clienteDAO;
	
	
	public ListadoClientesPresenter(IViewClientes ventanaClientes) {
		this.ventanaClientes = ventanaClientes;
	}
	
	public void visualizarClientes() {
		clienteDAO = new ClienteDAOImpl(ManagerContext.getInstance().getEntityManager());
		List<Cliente> listadoClientes = clienteDAO.obtenerLista();
		cargarModeloFor(listadoClientes);

	}


	public void buscarClientesBy(String nombre) {
		clienteDAO = new ClienteDAOImpl(ManagerContext.getInstance().getEntityManager());
		List<Cliente> listadoCliente = clienteDAO.buscarXNombre(nombre);
		cargarModeloFor(listadoCliente);
	}

	
	
	private void cargarModeloFor(List<Cliente> listadoClientes) {
		DefaultTableModel model = (DefaultTableModel)ventanaClientes.getTable().getModel();
		for (Cliente cliente : listadoClientes) {
			Object[] data = new Object[5];
			data[0] = cliente.getId();
			data[1] = cliente.getDni();
			data[2] = cliente.getEmail();
			data[3] = cliente.getEstado();
			data[4] = cliente.getNombre();
			model.addRow(data);
		}
	}
}
