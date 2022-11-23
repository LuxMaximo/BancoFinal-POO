package ar.edu.unju.fi.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unju.fi.presenter.ClientePresenter;
import ar.edu.unju.fi.presenter.views.IViewCliente;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.UIManager;

@SuppressWarnings("serial")
public class AltaClienteFrame extends JFrame implements IViewCliente{

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textDNI;
	private JTextField textEmail;
	//private JTextField textEstado;
	
	private ClientePresenter clientePresenter;
	private IViewCliente formularioAltaCliente;
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaClienteFrame frame = new AltaClienteFrame();
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
	public AltaClienteFrame() {
		
		clientePresenter = new ClientePresenter(this);
		
		setTitle("Alta Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre*");
		lblNombre.setForeground(UIManager.getColor("Table.foreground"));
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBounds(32, 294, 90, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDNI = new JLabel("DNI*");
		lblDNI.setForeground(UIManager.getColor("Table.foreground"));
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDNI.setBounds(32, 333, 46, 14);
		contentPane.add(lblDNI);
		
		JLabel lblCorreo = new JLabel("Email*");
		lblCorreo.setForeground(UIManager.getColor("Table.foreground"));
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCorreo.setBounds(32, 373, 71, 14);
		contentPane.add(lblCorreo);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNombre.setBounds(132, 291, 180, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textDNI = new JTextField();
		textDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textDNI.setBounds(132, 330, 180, 20);
		contentPane.add(textDNI);
		textDNI.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textEmail.setColumns(10);
		textEmail.setBounds(132, 370, 180, 20);
		contentPane.add(textEmail);
		


		JButton btnGuardar =  new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 17));

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					registrarNuevoCliente();	
							

			}
		});
		btnGuardar.setBounds(113, 425, 133, 47);
		contentPane.add(btnGuardar);
		/*
		JLabel lblEstado = new JLabel("Estado*");
		lblEstado.setForeground(UIManager.getColor("Table.foreground"));
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEstado.setBounds(32, 412, 71, 14);
		contentPane.add(lblEstado);
		
		textEstado = new JTextField();
		textEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textEstado.setBounds(132, 409, 180, 20);
		contentPane.add(textEstado);
		textEstado.setColumns(10);*/
		
		JLabel lblNewLabel = new JLabel(""); 
		Image img = new ImageIcon(this.getClass().getResource("/img/new.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(58, 27, 256, 238);
		contentPane.add(lblNewLabel);
	}
	
	
	//-------------METODOS------------
	
	private void registrarNuevoCliente() {
		Long dni = Long.parseLong(textDNI.getText());
		clientePresenter.registrarCliente(textNombre.getText(), textEmail.getText(), dni);
		this.dispose();
		}

	
	@Override
	public void visualizarResultado(String mensajeResultado) {
			JOptionPane.showMessageDialog(this, mensajeResultado);
	}


	@Override
	public void setInputsText(String nombre, String direccion, Long dni, boolean estado) {
		String modo;
		this.textNombre.setText(nombre);
		this.textEmail.setText(direccion);
		this.textDNI.setText(dni.toString());
	}
}
