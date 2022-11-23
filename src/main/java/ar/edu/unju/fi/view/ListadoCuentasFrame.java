package ar.edu.unju.fi.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unju.fi.presenter.ListadoCuentasPresenter;
import ar.edu.unju.fi.presenter.views.IViewCuentas;
import ar.edu.unju.fi.view.ListadoCuentasFrame;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;

public class ListadoCuentasFrame extends JDialog implements IViewCuentas{

	private JPanel contentPane;
	private ListadoCuentasPresenter presenter;
	private JTable table;
	private JTextField txtBuscarNumCuenta;
	private JTextField txtBuscarTipoCuenta;

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
					ListadoCuentasFrame frame = new ListadoCuentasFrame(null);
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
	public ListadoCuentasFrame(Integer idCliente) {
		setTitle("Listado de Cuentas Bancarias");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 916, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		presenter = new ListadoCuentasPresenter(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 740, 350);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(true);
		setTableModelFor(table);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(775, 11, 96, 85);
		contentPane.add(lblNewLabel);
		
		
		txtBuscarTipoCuenta = new JTextField();
		txtBuscarTipoCuenta.setBounds(10, 10, 156, 27);
		contentPane.add(txtBuscarTipoCuenta);
		txtBuscarTipoCuenta.setColumns(10);
		
		txtBuscarNumCuenta = new JTextField();
		txtBuscarNumCuenta.setBounds(394, 11, 156, 26);
		contentPane.add(txtBuscarNumCuenta);
		txtBuscarNumCuenta.setColumns(10);
		
		
		JButton btnAgregar = new JButton("Agregar Cuenta");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
		btnAgregar.setBounds(764, 256, 126, 40);
		contentPane.add(btnAgregar);
		
		
		JButton btnDepositar = new JButton("DEPOSITAR");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					Integer idCliente = (Integer) table.getModel().getValueAt(row, 0);
					
					DepositoFrame frame = new DepositoFrame();
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					
					setTableModelFor(table);
					visualizarListadoCuentas();
				}
			}
		});
		btnDepositar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDepositar.setBounds(764, 180, 126, 40);
		contentPane.add(btnDepositar);
		
		
		JButton btnExtraer = new JButton("EXTRAER");
		btnExtraer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					Integer idCuentaBancaria = (Integer) table.getModel().getValueAt(row, 0);
					
					ExtraccionFrame frame = new ExtraccionFrame(idCuentaBancaria);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setModal(true);
					frame.setVisible(true);
				
					setTableModelFor(table);
					visualizarListadoCuentas();
				}else {
					JOptionPane.showMessageDialog(null, "No selecciono ninguna cuenta.");
				}
			}
		});
		btnExtraer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExtraer.setBounds(764, 129, 126, 40);
		contentPane.add(btnExtraer);
		
		
		//Buscar el numero de Cuenta
		JButton btnBuscar = new JButton("Buscar Numero de Cuenta");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarXNumeroCuenta();
			}
		});
		btnBuscar.setBounds(560, 11, 190, 26);
		contentPane.add(btnBuscar);
		Image img = new ImageIcon(this.getClass().getResource("/img/banco.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		
		
		JButton btnBuscarTipo = new JButton("Buscar Tipo de Cuenta");
		btnBuscarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarXTipoCuenta();
			}
		});
		btnBuscarTipo.setBounds(176, 9, 168, 28);
		contentPane.add(btnBuscarTipo);
		

		
		
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
		presenter.visualizarCuentas();
	}

	public void buscarXNumeroCuenta() {
		Integer numCuenta = Integer.parseInt(this.txtBuscarNumCuenta.getText());
		presenter.buscarCuentaXNumero(numCuenta);
	}
	
	public void buscarXTipoCuenta() {
		presenter.buscarCuentaXTipo(this.txtBuscarTipoCuenta.getText());
	}
}
