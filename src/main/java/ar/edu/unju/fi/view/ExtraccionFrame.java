package ar.edu.unju.fi.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

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
import ar.edu.unju.fi.presenter.views.IViewCliente;
import ar.edu.unju.fi.presenter.views.IViewCuentaBancaria;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExtraccionFrame extends JDialog implements IViewCuentaBancaria{

	private JPanel contentPane;
	private JTextField textImporte;
	private JTextField textSaldo;
	private JTextField textNombre;
	
	private CuentaBancariaPresenter bancoPresenter;
	private IViewCliente formularioAltaCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExtraccionFrame frame = new ExtraccionFrame(null);
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
	public ExtraccionFrame(Integer idCuentaBancaria) {
		
		bancoPresenter = new CuentaBancariaPresenter(this);
		
		setTitle("Extraccion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 312, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/img/plata.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(79, 28, 132, 110);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblImporte = new JLabel("Importe $");
		lblImporte.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblImporte.setBounds(23, 276, 110, 38);
		contentPane.add(lblImporte);
		
		JLabel lblSaldo = new JLabel("Saldo $");
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSaldo.setBounds(23, 211, 110, 38);
		contentPane.add(lblSaldo);
		
		
		textImporte = new JTextField();
		textImporte.setColumns(10);
		textImporte.setBounds(130, 282, 116, 32);
		contentPane.add(textImporte);
		
		

		textSaldo = new JTextField();
		textSaldo.setColumns(10);
		textSaldo.setBounds(130, 211, 116, 32);
		contentPane.add(textSaldo);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(45, 163, 215, 20);
		contentPane.add(textNombre);
		
		
		JButton btnExtraer = new JButton("Extraer");
		btnExtraer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extraccionXcajaAhorro(idCuentaBancaria, Double.parseDouble(textImporte.getText()));
			}
		});
		btnExtraer.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExtraer.setBounds(89, 347, 111, 54);
		contentPane.add(btnExtraer);
		
	}
	

	@Override
	public JComboBox<Cliente> getComboClientes() {
		return null;
	}

	@Override
	public void setInputsText(String nombre, Double saldo) {
		this.textSaldo.setText(saldo.toString());
		this.textNombre.setText(nombre);
		
	}
	
	public void extraccionXcajaAhorro(Integer idCuenta, Double monto) {
		bancoPresenter.extraerXCajaAhorro(idCuenta, monto);
		this.dispose();
	}
	
}
