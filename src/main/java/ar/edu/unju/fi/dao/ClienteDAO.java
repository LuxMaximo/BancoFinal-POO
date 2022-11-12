package ar.edu.unju.fi.dao;

import java.util.List;

import ar.edu.unju.fi.model.Cliente;

public interface ClienteDAO {

	List<Cliente> obtenerLista();

	void guardar(Cliente cliente);

	List<Cliente> buscarXNombre(String nombre);

	Cliente buscarXID(Integer idCliente);

	void actualizar(Cliente clienteModel);
	
}
