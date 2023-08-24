package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

public class WebSocket implements Runnable {

	private static final int serverport = 8080;
	private static ServerSocket server;

	public WebSocket(JTextArea campoDeTexto) throws IOException {
		if (server == null) {
			WebSocket.server = new ServerSocket(serverport);
		}
	}

	@Override
	public void run() {
		System.out.println("Servidor na porta: " + server.getLocalPort());
		while (true) {
			try (Socket client = server.accept()) {
				Requests requests = new Requests(client);
				requests.run();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println();
		}
	}
}