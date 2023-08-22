package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class viewMesseger {

	private JFrame frame;
	private JTextField textMensagem;
	private JTextField textIP;
	private JTextField textNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewMesseger window = new viewMesseger();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public viewMesseger() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 11, 714, 153);
		frame.getContentPane().add(textArea);
		
		textMensagem = new JTextField();
		textMensagem.setBounds(77, 196, 647, 20);
		frame.getContentPane().add(textMensagem);
		textMensagem.setColumns(10);
		
		JLabel lbMensagem = new JLabel("Mensagem:");
		lbMensagem.setBounds(10, 199, 65, 14);
		frame.getContentPane().add(lbMensagem);
		
		textIP = new JTextField();
		textIP.setBounds(77, 250, 339, 20);
		frame.getContentPane().add(textIP);
		textIP.setColumns(10);
		
		JLabel lbEndereco = new JLabel("Endere\u00E7o:");
		lbEndereco.setBounds(10, 253, 65, 14);
		frame.getContentPane().add(lbEndereco);
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setBounds(10, 306, 46, 14);
		frame.getContentPane().add(lbNome);
		
		textNome = new JTextField();
		textNome.setColumns(10);
		textNome.setBounds(77, 303, 339, 20);
		frame.getContentPane().add(textNome);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnviar.setBounds(327, 374, 89, 23);
		frame.getContentPane().add(btnEnviar);
	}
}
