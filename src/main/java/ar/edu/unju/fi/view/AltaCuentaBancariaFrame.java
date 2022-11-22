package ar.edu.unju.fi.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.edu.unju.fi.model.Cliente;
import ar.edu.unju.fi.model.CuentaBancaria;
import ar.edu.unju.fi.presenter.CuentaBancariaPresenter;
import ar.edu.unju.fi.presenter.views.IViewCuentaBancaria;

public class AltaCuentaBancariaFrame extends JDialog implements IViewCuentaBancaria {

	private JPanel contentPane;
	private JTextField txtNumeroCuenta;
	private JTextField txtSaldo;
	private JComboBox<String> comboTipoCuenta;
	private JComboBox<Cliente> comboClientes;
	
	private CuentaBancariaPresenter cuentaBancariaPresenter;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaCuentaBancariaFrame frame = new AltaCuentaBancariaFrame(null);
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
	public AltaCuentaBancariaFrame(Integer idCuenta) {
		
		cuentaBancariaPresenter = new CuentaBancariaPresenter(this);
		
		setTitle("Alta Cuenta Bancaria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 100, 393, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Tipo");
		lblNombre.setBounds(10, 8, 70, 14);
		contentPane.add(lblNombre);		
		
		
		comboTipoCuenta = new JComboBox<String>();
		comboTipoCuenta.setBounds(100, 8, 150, 20);
		comboTipoCuenta.addItem("CAJA-AHORRO");
		comboTipoCuenta.addItem("CUENTA-CORRIENTE");		
		contentPane.add(comboTipoCuenta);
		
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 36, 98, 14);
		contentPane.add(lblCliente);
		
		
		comboClientes = new JComboBox<Cliente>();
		comboClientes.setBounds(100,36, 150, 20);
		cuentaBancariaPresenter.cargarComboTitulares();
		contentPane.add(comboClientes);
		
		
		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(10, 70, 70, 14);
		contentPane.add(lblSaldo);		
		txtSaldo = new JTextField();
		
		
		txtSaldo.setBounds(100, 67, 104, 20);
		contentPane.add(txtSaldo);
		txtSaldo.setColumns(10);
		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

						registrarCuentaBancaria();
			}
		});
		btnGuardar.setBounds(131, 106, 119, 23);
		contentPane.add(btnGuardar);
	}

	@Override
	public JComboBox<Cliente> getComboClientes() {
		return comboClientes;
	}

	private void registrarCuentaBancaria() {
		cuentaBancariaPresenter.registrarCuentaBancaria(comboTipoCuenta.getSelectedItem().toString(), (Cliente) comboClientes.getSelectedItem(), txtSaldo.getText());
		this.dispose();
	}


}
