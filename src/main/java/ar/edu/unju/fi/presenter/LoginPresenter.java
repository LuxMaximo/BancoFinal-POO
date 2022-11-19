package ar.edu.unju.fi.presenter;

import ar.edu.unju.fi.dao.UsuarioDAO;
import ar.edu.unju.fi.dao.impl.UsuarioDAOImpl;
import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.presenter.views.IViewlogin;
import ar.edu.unju.fi.util.ManagerContext;

public class LoginPresenter {
	private IViewlogin formularioLogin;
	private UsuarioDAO usuarioDAO;
	
	public LoginPresenter(IViewlogin formularioLogin) {
		this.formularioLogin = formularioLogin;
		this.usuarioDAO = new UsuarioDAOImpl(ManagerContext.getInstance().getEntityManager());
	}

	public void validar(String login, String password) {
		
		Usuario usuario = usuarioDAO.buscarXLoginYPassword(login, password);
		if (usuario != null) {
			formularioLogin.visualizarMain();
		} else {
			formularioLogin.visualizarResultado("Los Datos de Acceso son Incorrectos");
		}
	}
}