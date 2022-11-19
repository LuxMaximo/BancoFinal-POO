package ar.edu.unju.fi.dao;

import java.util.List;

import ar.edu.unju.fi.model.Usuario;

public interface UsuarioDAO {
	
	List<Usuario> obtenerLista();

	void guardar(Usuario usuario);

	Usuario buscarXLoginYPassword(String login, String password);
}
