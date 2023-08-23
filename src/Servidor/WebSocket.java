package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JTextArea;

public class WebSocket implements Runnable {

	private static final int serverport = 8080;
	private static ServerSocket server;
	private static JTextArea campoDeTexto;
	private static HashMap<String, Socket> clients = new HashMap<String, Socket>();

	public WebSocket(JTextArea campoDeTexto) throws IOException {
		if (server == null) {
			this.server = new ServerSocket(serverport);
		}
		this.campoDeTexto = campoDeTexto;
	}

	@Override
	public void run() {
		System.out.println("Servidor na porta: " + server.getLocalPort());
		while (true) {
			try (Socket client = server.accept()) {
				Requests requests = new Requests(client, campoDeTexto, clients);
				requests.run();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(clients.size());
		}
	}
}