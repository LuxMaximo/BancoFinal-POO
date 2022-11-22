package ar.edu.unju.fi.presenter.views;

import javax.swing.JTable;

public interface IViewCuentas {
	void visualizarListadoCuentas();
	JTable getTable();
	void setTable(JTable table);

}
