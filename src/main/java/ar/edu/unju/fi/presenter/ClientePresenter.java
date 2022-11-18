package ar.edu.unju.fi.presenter;

import ar.edu.unju.fi.dao.ClienteDAO;
import ar.edu.unju.fi.dao.impl.ClienteDAOImpl;
import ar.edu.unju.fi.model.Cliente;
import ar.edu.unju.fi.presenter.views.IViewCliente;
import ar.edu.unju.fi.util.ManagerContext;

public class ClientePresenter {
	private IViewCliente formularioAltaCliente;
	private ClienteDAO clienteDAO;
	private Cliente clienteModel;
	
	
	public ClientePresenter(IViewCliente formularioAltaCliente) {
		super();
		this.formularioAltaCliente = formularioAltaCliente;
		this.clienteDAO = new ClienteDAOImpl(ManagerContext.getInstance().getEntityManager());
	}
	
	public void registrarCliente(String nombre, String email, Long dni) {
		clienteModel = new Cliente(dni, nombre, email);
		clienteDAO.guardar(clienteModel);
		
		
		formularioAltaCliente.visualizarResultado("El siguiente cliente ha sido registrado: \n"+
				   "Nombre: " + clienteModel.getNombre()+"\n"+
				   "Email: " + clienteModel.getEmail() + "\n"+
				   "DNI: " + clienteModel.getDni());
	}
	
	public void buscarByNombre(String nombre) {
		Cliente cliente = (Cliente) clienteDAO.buscarXNombre(nombre);
		formularioAltaCliente.setInputsText(cliente.getNombre(),  cliente.getEmail(), cliente.getDni(), cliente.getEstado());		
	}

	public void buscarByID(Integer id) {
		Cliente cliente = (Cliente)clienteDAO.buscarXID(id);
		formularioAltaCliente.setInputsText(cliente.getNombre(),  cliente.getEmail(), cliente.getDni(), cliente.getEstado());	
	}
	public void actualizarCliente(Integer idCliente, String nombre, String email , Long dni) {
		clienteModel = new Cliente(dni, nombre, email);
		clienteModel.setId(idCliente);
		clienteDAO.actualizar(clienteModel);
	}
	
	
}
