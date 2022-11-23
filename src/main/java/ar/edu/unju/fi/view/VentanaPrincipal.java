package ar.edu.unju.fi.view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 213);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEntidades = new JMenu("Menú");
		menuBar.add(mnEntidades);
		
		JMenu mnCliente = new JMenu("Clientes");
		mnEntidades.add(mnCliente);
		
		JMenuItem mntmAltaCliente = new JMenuItem("+ Nuevo Cliente");
		mntmAltaCliente.setForeground(new Color(0, 100, 0));
		mntmAltaCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				altaCliente();
			}
		});
		
		mnCliente.add(mntmAltaCliente);
		
		JMenuItem mntmListarClientes = new JMenuItem("Listar Clientes");
		mntmListarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarLista();
			}
		});
		mnCliente.add(mntmListarClientes);
		
		
		JMenu mnCuentaBancaria = new JMenu("Cuentas Bancarias");
		mnEntidades.add(mnCuentaBancaria);
		
		
		
		JMenuItem mntmAltaCuentaBancaria = new JMenuItem("+ Nueva Cuenta Bancaria");
		mntmAltaCuentaBancaria.setForeground(new Color(0, 100, 0));
		mntmAltaCuentaBancaria.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				altaCuenta();		
			}
		});
		mnCuentaBancaria.add(mntmAltaCuentaBancaria);
		
		JMenuItem mntmListaCuentasBancarias = new JMenuItem("listar Cuenta bancaria");
		mntmListaCuentasBancarias.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarCuentas();		
			}
		});
		mnCuentaBancaria.add(mntmListaCuentasBancarias);
		
		
		
		JMenuItem mntmCerrar = new JMenuItem("Salir del programa");
		mntmCerrar.setForeground(new Color(220, 20, 60));
		mntmCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		mnEntidades.add(mntmCerrar);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/img/banco.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(85, 24, 96, 104);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Banco Departamental");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(221, 39, 232, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\"San Pedro de Jujuy\"");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_2.setBounds(251, 81, 183, 14);
		contentPane.add(lblNewLabel_2);
	
		
	}
	
	
	
	//-------------METODOS------------
	public void altaCliente() {
		AltaClienteFrame altaClienteFrame = new AltaClienteFrame();
		
		altaClienteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		altaClienteFrame.setVisible(true);
	}
	
	public void mostrarLista() {
		ListadoClientesFrame ls = new ListadoClientesFrame();
		ls.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ls.setVisible(true);
	}
	
	public void altaCuenta() {
		AltaCuentaBancariaFrame altaCuentaBancariaFrame = new AltaCuentaBancariaFrame(null);
		altaCuentaBancariaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		altaCuentaBancariaFrame.setVisible(true);
	}
	
	public void mostrarCuentas() {
		ListadoCuentasFrame listadoCuentasFrame = new ListadoCuentasFrame();
		listadoCuentasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		listadoCuentasFrame.setVisible(true);
	}
}
