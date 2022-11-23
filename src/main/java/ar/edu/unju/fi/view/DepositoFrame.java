package ar.edu.unju.fi.view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unju.fi.model.CuentaBancaria;
import ar.edu.unju.fi.presenter.ListadoCuentasPresenter;
import ar.edu.unju.fi.presenter.views.IViewCuentas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

public class DepositoFrame extends JFrame implements IViewCuentas{

	private JPanel contentPane;
	private JTextField txtImporte;
	private JTable table;
	
	private ListadoCuentasPresenter presenter;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepositoFrame frame = new DepositoFrame();
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
	public DepositoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/img/safe2.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(96, 42, 132, 134);
		contentPane.add(lblNewLabel);
		table = new JTable();
		table.setRowSelectionAllowed(true);
		setTableModelFor(table);
		
		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					Integer idCliente =  (Integer) table.getModel().getValueAt(row, 0);
					ListadoCuentasFrame frame = new ListadoCuentasFrame(idCliente);
					frame.setModal(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					table = new JTable();
					table.setRowSelectionAllowed(true);
					setTableModelFor(table);
					
					depositar();
					visualizarListadoCuentas();
				}else {
					JOptionPane.showMessageDialog(null, "No selecciono ningun cliente.");
				}
			}
		});
		btnDepositar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDepositar.setBounds(109, 275, 132, 54);
		contentPane.add(btnDepositar);
		
		JLabel lblImporte = new JLabel("Importe $");
		lblImporte.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblImporte.setBounds(47, 205, 99, 38);
		contentPane.add(lblImporte);
		
		txtImporte = new JTextField();
		txtImporte.setBounds(156, 211, 132, 32);
		contentPane.add(txtImporte);
		txtImporte.setColumns(10);
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

	@Override
	public JTable getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTable(JTable table) {
		// TODO Auto-generated method stub
		
	}
	
	public void depositar() {
		CuentaBancaria cuenta = new CuentaBancaria();
		String importe = txtImporte.getText();
		txtImporte.setText(importe + cuenta.getSaldo());

	}
}
