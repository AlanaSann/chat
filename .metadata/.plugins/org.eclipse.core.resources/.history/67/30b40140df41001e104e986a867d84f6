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
	private String mensagem;

	public EnviadorDeMensagens(String endereco, int port, String mensagem) throws UnknownHostException, IOException {
		this.endereco = endereco;
		this.port = port;
		this.mensagem = mensagem;
		this.socket = new Socket(this.endereco, this.port);
	}

	public void enviaMensagem() throws IOException {
		BufferedReader leitor;
		leitor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
		escritor.println(mensagem);
		String retorno;
		while (true) {
			retorno = leitor.readLine();
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