package ar.edu.unju.fi.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
		setBounds(100, 100, 647, 187);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEntidades = new JMenu("Entidades");
		menuBar.add(mnEntidades);
		
		JMenu mnCliente = new JMenu("Clientes");
		mnEntidades.add(mnCliente);
		
		JMenuItem mntmAltaCliente = new JMenuItem("Crear nuevo cliente");
		mntmAltaCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				altaCliente();
			}
		});
		
		mnCliente.add(mntmAltaCliente);
		
		JMenuItem mntmListarClientes = new JMenuItem("Ver la lista de clientes");
		mntmListarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarLista();
			}
		});
		mnCliente.add(mntmListarClientes);
		
		
		JMenu mnCuentaBancaria = new JMenu("Cuentas bancarias");
		mnEntidades.add(mnCuentaBancaria);
		
		
		
		JMenu mnAltaCuentaBancaria = new JMenu("Crear nueva cuenta bancaria");
		mnCuentaBancaria.add(mnAltaCuentaBancaria);
		
		
		
		JMenuItem mntmCuentaCorriente = new JMenuItem("Cuenta corriente");
		mnAltaCuentaBancaria.add(mntmCuentaCorriente);
		
		
		
		JMenuItem mntmCajaAhorro = new JMenuItem("Caja de ahorro");
		mnAltaCuentaBancaria.add(mntmCajaAhorro);
		
		
		
		JMenuItem mntmCerrar = new JMenuItem("Salir del programa");
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
	}
	
	
	
	//-------------METODOS------------
	public void altaCliente() {
		AltaClienteFrame altaClienteFrame = new AltaClienteFrame(null);
		altaClienteFrame.setModal(true);
		altaClienteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		altaClienteFrame.setVisible(true);
	}
	
	public void mostrarLista() {
		ListadoClientesFrame ls = new ListadoClientesFrame();
		ls.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ls.setVisible(true);
	}
}
