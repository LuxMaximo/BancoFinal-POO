package ar.edu.unju.fi.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unju.fi.view.ListadoClientesFrame;
import ar.edu.unju.fi.presenter.LoginPresenter;
import ar.edu.unju.fi.presenter.views.IViewlogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginFrame extends JFrame implements IViewlogin{

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private LoginPresenter loginPresenter;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setTitle("Ingreso Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(33, 11, 78, 42);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(106, 21, 140, 25);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(29, 67, 76, 26);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(107, 68, 138, 26);
		contentPane.add(txtPassword);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//login();
				loginPresenter.validar(txtUsuario.getText(), txtPassword.getText());
			}
		});
		btnIngresar.setBounds(101, 109, 104, 31);
		contentPane.add(btnIngresar);
	}

	@Override
	public void visualizarResultado(String resultado) {
		JOptionPane.showMessageDialog(this, resultado);
		txtUsuario.setText(null);
		txtPassword.setText(null);
	}

	@Override
	public void visualizarMain() {
		this.setVisible(false);
		ListadoClientesFrame  titulares = new ListadoClientesFrame();
		titulares.setVisible(true);
	}
}
