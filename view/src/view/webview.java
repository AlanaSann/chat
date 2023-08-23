package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

//import Client.EnviadorDeMensagens;
//import Servidor.WebSocket;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class webview {

	private JFrame frame;
	private JTextField textMensagem;
	private JTextField textPorta;
	private JTextField textNome;
	private Thread threadServidor;
	private Thread threadSender;
	private JTextField textField;
	//private WebSocket webSocket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					webview window = new webview();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public webview() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
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

		textPorta = new JTextField();
		textPorta.setBounds(470, 247, 184, 20);
		frame.getContentPane().add(textPorta);
		textPorta.setColumns(10);

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
				//try {
				//	EnviadorDeMensagens enviadorDeMensagens = new EnviadorDeMensagens(textIP.getText(),8080,textMensagem.getText());
				//	threadSender = new Thread(enviadorDeMensagens);
					//threadSender.start();
				//} catch (IOException e1) {
				//}
			//}
		}});
		//webSocket = new WebSocket(textArea);
		//threadServidor = new Thread(webSocket);
		threadServidor.start();
		btnEnviar.setBounds(327, 374, 89, 23);
		frame.getContentPane().add(btnEnviar);
		
		JLabel lbPorta = new JLabel("Porta:");
		lbPorta.setBounds(436, 250, 37, 14);
		frame.getContentPane().add(lbPorta);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(77, 247, 339, 20);
		frame.getContentPane().add(textField);
			}
		}
