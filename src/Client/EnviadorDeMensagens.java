package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EnviadorDeMensagens implements Runnable {

	private String endereco;
	private int port;
	private Socket socket;
	private String mensagem;

	public EnviadorDeMensagens(String endereco, int port, String mensagem) throws UnknownHostException, IOException {
		this.endereco = endereco;
		this.port = port;
		this.mensagem = mensagem;
		this.socket = new Socket(this.endereco, this.port);
	}

	public void enviaMensagem() throws IOException {
		PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
		escritor.println(mensagem);
		System.out.println(mensagem);
		socket.close();
	}

	@Override
	public void run() {
		try {
			this.enviaMensagem();
		} catch (IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

}