package ar.edu.unju.fi.presenter.views;

import javax.swing.JComboBox;

import ar.edu.unju.fi.model.Cliente;

public interface IViewCuentaBancaria {
	JComboBox<Cliente> getComboClientes();
	
	void setInputsText(String nombre, Double saldo);
}
