package ar.edu.unju.fi.presenter.views;

public interface IViewCliente {
	void visualizarResultado(String mensajeResultado);
	
	void setInputsText(String nombre, String direccion, Long dni, boolean estado);
}
