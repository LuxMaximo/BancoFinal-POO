package ar.edu.unju.fi.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unju.fi.presenter.ListadoClientesPresenter;
import ar.edu.unju.fi.presenter.views.IViewClientes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ListadoClientesFrame extends JFrame implements IViewClientes{

	private JPanel contentPane;
	private ListadoClientesPresenter presenter;
	private JTable table;

	@Override
	public JTable getTable() {
		return table;
	}
	
	@Override
	public void setTable(JTable table) {
		this.table = table;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoClientesFrame frame = new ListadoClientesFrame();
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
	public ListadoClientesFrame() {
		
		presenter = new ListadoClientesPresenter(this);
		
		
		setTitle("Listado de Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Titulo
		JLabel lblTitulo = new JLabel("Listado Clientes");
		lblTitulo.setBounds(10, 14, 102, 14);
		contentPane.add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 47, 725, 344);
		
		
		
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "DNI", "Correo Electronico", "Estado", "Nombre"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setPreferredWidth(96);
		table.getColumnModel().getColumn(2).setPreferredWidth(187);
		table.getColumnModel().getColumn(3).setPreferredWidth(52);
		table.getColumnModel().getColumn(4).setPreferredWidth(134);
		scrollPane.setViewportView(table);
		
		visualizarListadoClientes();

		
		
		//Boton Agregar
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaClienteFrame altaClienteFrame = new AltaClienteFrame(null);
				altaClienteFrame.setModal(true);
				altaClienteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				altaClienteFrame.setVisible(true);
				setTableModelFor(table);

				visualizarListadoClientes();
			}
		});
		btnAgregar.setBounds(180, 10, 89, 23);
		contentPane.add(btnAgregar);
		
		
		//Boton Editar
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					Integer idCliente = (Integer) table.getModel().getValueAt(row, 0);
					
					AltaClienteFrame altaCliente = new AltaClienteFrame(idCliente);
					altaCliente.setModal(true);
					altaCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					altaCliente.setVisible(true);
					setTableModelFor(table);

					visualizarListadoClientes();
				}else {
					JOptionPane.showMessageDialog(null, "No selecciono ningun cliente.");
				}
			}
		});
		btnEditar.setBounds(336, 10, 89, 23);
		contentPane.add(btnEditar);
		
	}
	
	//---------------Metodos----------
	
	private void setTableModelFor(JTable table) {
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "DNI", "Correo Electronico", "Estado", "Nombre"
					}
				) {
					boolean[] columnEditables = new boolean[] {
						false, true, true, true, true
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setPreferredWidth(45);
				table.getColumnModel().getColumn(1).setPreferredWidth(96);
				table.getColumnModel().getColumn(2).setPreferredWidth(187);
				table.getColumnModel().getColumn(3).setPreferredWidth(52);
				table.getColumnModel().getColumn(4).setPreferredWidth(134);

				visualizarListadoClientes();

	}
	
	@Override
	public void visualizarListadoClientes() {
		presenter.visualizarClientes();
		
	}
}
