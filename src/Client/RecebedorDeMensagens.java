package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

public class RecebedorDeMensagens implements Runnable {

    private static JTextArea textArea;
    private static final int serverport = 4321;
    private static ServerSocket server;

    public RecebedorDeMensagens(JTextArea textArea) throws IOException {
        if (server == null) {
            RecebedorDeMensagens.server = new ServerSocket(serverport);
        }
        RecebedorDeMensagens.textArea = textArea;
    }

    public void emAguardoDeMensagens() {
        try {
            Socket client = server.accept();
            System.out.println("Cliente " + client.getInetAddress().getHostAddress() + " conectado");
            BufferedReader leitor = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String mensagem = leitor.readLine();
            System.out.println("Mensagem Recebida " + mensagem);
            textArea.setText(textArea.getText() + mensagem + " " + client.getInetAddress().getHostAddress() + "\n");
            client.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void run() {
        System.out.println("Recebendo na porta: " + server.getLocalPort());
        while (true) {
            this.emAguardoDeMensagens();
        }
    }

}
