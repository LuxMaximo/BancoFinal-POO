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
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ListadoClientesFrame extends JFrame implements IViewClientes{

	private JPanel contentPane;
	private ListadoClientesPresenter presenter;
	private JTable table;
	private JTextField textBuscar;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		btnEditar.setBounds(133, 10, 89, 23);
		contentPane.add(btnEditar);
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTableModelFor(table);
				presenter.buscarClientesBy(textBuscar.getText());
			}
		});
		btnBuscar.setBounds(690, 10, 89, 23);
		contentPane.add(btnBuscar);
		
		textBuscar = new JTextField();
		textBuscar.setBounds(496, 11, 183, 20);
		contentPane.add(textBuscar);
		textBuscar.setColumns(10);
		
		
		
		table = new JTable();
		table.setRowSelectionAllowed(true);
		setTableModelFor(table);
		/*table.setModel(new DefaultTableModel(
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
		*/
		scrollPane.setViewportView(table);
		
		visualizarListadoClientes();
		
		
		
		
		
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
	}
	
	@Override
	public void visualizarListadoClientes() {
		presenter.visualizarClientes();
		
	}
}
