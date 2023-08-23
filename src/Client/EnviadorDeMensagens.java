package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EnviadorDeMensagens implements Runnable {

	private String endereco;
	private int port;
	private Socket socket;

	public EnviadorDeMensagens(String endereco, int port) throws UnknownHostException, IOException {
		this.endereco = endereco;
		this.port = port;
		this.socket = new Socket(this.endereco, this.port);
	}

	public void enviaMensagem() throws IOException {
		BufferedReader leitor;
		leitor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
		escritor.println("Estou conectando");
		String mensagem;
		while (true) {
			mensagem = leitor.readLine();
		}
		// System.out.println(mensagem);
		// socket.close();
	}

	@Override
	public void run() {
		try {
			this.enviaMensagem();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}