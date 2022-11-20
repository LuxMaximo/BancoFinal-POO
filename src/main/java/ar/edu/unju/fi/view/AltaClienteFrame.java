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
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AltaClienteFrame extends JFrame implements IViewCliente{

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textDNI;
	private JTextField textEmail;
	
	private ClientePresenter clientePresenter;
	private IViewCliente formularioAltaCliente;
	private JTextField textEstado;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaClienteFrame frame = new AltaClienteFrame(null);
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
	public AltaClienteFrame(Integer idCliente) {
		
		clientePresenter = new ClientePresenter(this);
		
		setTitle("Alta Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 278);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre*");
		lblNombre.setBounds(20, 11, 61, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDNI = new JLabel("DNI*");
		lblDNI.setBounds(20, 50, 46, 14);
		contentPane.add(lblDNI);
		
		JLabel lblCorreo = new JLabel("Email*");
		lblCorreo.setBounds(20, 90, 46, 14);
		contentPane.add(lblCorreo);
		
		textNombre = new JTextField();
		textNombre.setBounds(120, 8, 180, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textDNI = new JTextField();
		textDNI.setBounds(120, 47, 180, 20);
		contentPane.add(textDNI);
		textDNI.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(120, 87, 180, 20);
		contentPane.add(textEmail);
		
		
		
		JButton btnGuardar = new JButton("Guardar");

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (idCliente != null) {
					
					actualizarCliente(idCliente);
				}else {
					registrarNuevoCliente();	
				}				

			}
		});
		btnGuardar.setBounds(147, 194, 89, 23);
		contentPane.add(btnGuardar);
		
		JLabel lblEstado = new JLabel("Estado*");
		lblEstado.setBounds(20, 129, 46, 14);
		contentPane.add(lblEstado);
		
		textEstado = new JTextField();
		textEstado.setBounds(120, 126, 180, 20);
		contentPane.add(textEstado);
		textEstado.setColumns(10);
	}
	
	private void registrarNuevoCliente() {
		Long dni = Long.parseLong(textDNI.getText());
		clientePresenter.registrarCliente(textNombre.getText(), textEmail.getText(), dni);
		this.dispose();
		}


	private void buscarClienteBy(Integer idCliente) {
		if(idCliente != null) {
			clientePresenter.buscarByID(idCliente);	
		}		
	}
	
	private void actualizarCliente(Integer idCliente) {
		Long dni=0l;
		boolean estado = true;
		
		if (textEstado.getText().length()== 5) {
			estado = false;
		}
		clientePresenter.actualizarCliente(idCliente, textNombre.getText(), textEmail.getText(), dni.parseLong(textDNI.getText()), estado);
		System.out.println(dni);
		System.out.println(dni.TYPE);
		this.dispose();		
	}
	
	@Override
	public void visualizarResultado(String mensajeResultado) {
			JOptionPane.showMessageDialog(this, mensajeResultado);
	}
	
	
	
	public void setModal(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInputsText(String nombre, String direccion, Long dni, boolean estado) {
		this.textNombre.setText(nombre);
		this.textEmail.setText(direccion);
		
		
	}
}
