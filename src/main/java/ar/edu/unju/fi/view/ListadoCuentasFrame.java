package ar.edu.unju.fi.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unju.fi.presenter.ListadoCuentasPresenter;
import ar.edu.unju.fi.presenter.views.IViewCuentas;
import ar.edu.unju.fi.view.ListadoCuentasFrame;

public class ListadoCuentasFrame extends JDialog implements IViewCuentas{

	private JPanel contentPane;
	private ListadoCuentasPresenter presenter;
	private JTable table;

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoCuentasFrame frame = new ListadoCuentasFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public ListadoCuentasFrame() {
		setTitle("Listado de Cuentas Bancarias");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 737, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Listado de Cuentas Bancarias");
		lblTitulo.setBounds(10, 11, 212, 14);
		contentPane.add(lblTitulo);
		
		JButton btnAgregar = new JButton("Agregar Cuenta");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaCuentaBancariaFrame altaCuentaFrame = new AltaCuentaBancariaFrame(null);
				altaCuentaFrame.setModal(true);
				altaCuentaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				altaCuentaFrame.setVisible(true);
				setTableModelFor(table);
				visualizarListadoCuentas();
			}
		});
		btnAgregar.setBounds(332, 11, 180, 23);
		contentPane.add(btnAgregar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 673, 350);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(true);
		setTableModelFor(table);
		scrollPane.setViewportView(table);
		presenter = new ListadoCuentasPresenter(this);
	    visualizarListadoCuentas();
	}
	
	private void setTableModelFor(JTable table) {
		table.setModel(new DefaultTableModel(
			new Object[][] {
				},
				new String[] {
					"Id", "Titular", "Tipo", "Numero", "Saldo"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					true, true, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			table.getColumnModel().getColumn(1).setPreferredWidth(194);
			table.getColumnModel().getColumn(2).setResizable(false);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
	}

	@Override
	public void visualizarListadoCuentas() {
		// TODO Auto-generated method stub
		presenter.visualizarCuentas();
	}

	public void buscarXNumeroCuenta() {
		presenter.buscarCuentaXNumero(txtNumeroCuenta.getInt());
	}
}
