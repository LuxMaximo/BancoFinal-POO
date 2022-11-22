package ar.edu.unju.fi.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unju.fi.presenter.LoginPresenter;
import ar.edu.unju.fi.presenter.views.IViewlogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

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
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
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
		loginPresenter = new LoginPresenter(this);
		setTitle("Ingreso Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(204, 44, 78, 42);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsuario.setBounds(302, 55, 140, 25);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(183, 101, 111, 26);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setColumns(10);
		txtPassword.setBounds(304, 103, 138, 26);
		contentPane.add(txtPassword);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loginPresenter.validar(txtUsuario.getText(), txtPassword.getText());
			}
		});
		btnIngresar.setBounds(288, 166, 104, 31);
		contentPane.add(btnIngresar);
		//".img/src/main/java/ar/edu/unju/fi/view/secrecy-icon.png"
		//./img/../../../../../../../../secrecy-icon.png
		
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/img/secrecy-icon.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(35, 21, 138, 176);
		contentPane.add(lblNewLabel);
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
		VentanaPrincipal ventana = new VentanaPrincipal();
		ventana.setVisible(true);
	}
}
