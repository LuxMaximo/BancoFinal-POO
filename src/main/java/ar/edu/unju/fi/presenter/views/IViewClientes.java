package ar.edu.unju.fi.presenter.views;

import javax.swing.JTable;

public interface IViewClientes {
	void visualizarListadoClientes();
	
	JTable getTable();
	
	void setTable(JTable table);
}
