package ar.edu.unju.fi.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.edu.unju.fi.model.Cliente;
import ar.edu.unju.fi.presenter.CuentaBancariaPresenter;
import ar.edu.unju.fi.presenter.views.IViewCuentaBancaria;
import java.awt.Font;
import java.awt.Image;

public class AltaCuentaBancariaFrame extends JDialog implements IViewCuentaBancaria {

	private JPanel contentPane;
	private JTextField txtNumeroCuenta;
	private JTextField txtSaldo;
	private JComboBox<String> comboTipoCuenta;
	private JComboBox<Cliente> comboClientes;
	
	private CuentaBancariaPresenter cuentaBancariaPresenter;
	private JLabel lblNewLabel;
	
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
		setBounds(10, 100, 393, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Tipo");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBounds(109, 299, 32, 14);
		contentPane.add(lblNombre);		
		
		
		comboTipoCuenta = new JComboBox<String>();
		comboTipoCuenta.setBounds(151, 298, 150, 20);
		comboTipoCuenta.addItem("CAJA-AHORRO");
		comboTipoCuenta.addItem("CUENTA-CORRIENTE");		
		contentPane.add(comboTipoCuenta);
		
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCliente.setBounds(91, 327, 53, 14);
		contentPane.add(lblCliente);
		
		
		comboClientes = new JComboBox<Cliente>();
		comboClientes.setBounds(151,326, 150, 20);
		cuentaBancariaPresenter.cargarComboTitulares();
		contentPane.add(comboClientes);
		
		
		JLabel lblNumeroCuenta = new JLabel("Numero");
		lblNumeroCuenta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNumeroCuenta.setBounds(82, 355, 59, 14);
		contentPane.add(lblNumeroCuenta);	
		
		
		txtNumeroCuenta = new JTextField();
		txtNumeroCuenta.setBounds(151, 354, 104, 20);
		contentPane.add(txtNumeroCuenta);
		txtNumeroCuenta.setColumns(10);
		
		
		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaldo.setBounds(99, 379, 42, 14);
		contentPane.add(lblSaldo);		
		txtSaldo = new JTextField();
		
		
		txtSaldo.setBounds(151, 378, 104, 20);
		contentPane.add(txtSaldo);
		txtSaldo.setColumns(10);
		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarCuentaBancaria();	
			}
		});
		btnGuardar.setBounds(119, 422, 136, 42);
		contentPane.add(btnGuardar);
		
		lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/img/pig.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(56, 30, 245, 258);
		contentPane.add(lblNewLabel);
	}

	@Override
	public JComboBox<Cliente> getComboClientes() {
		return comboClientes;
	}

	private void registrarCuentaBancaria() {
		cuentaBancariaPresenter.registrarCuentaBancaria(comboTipoCuenta.getSelectedItem().toString(), comboClientes.getSelectedItem(), txtNumeroCuenta.getText(), txtSaldo.getText());
		this.dispose();
	}

}
