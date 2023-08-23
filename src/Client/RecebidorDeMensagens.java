package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import Servidor.Requests;

public class RecebidorDeMensagens implements Runnable {

    private static JTextArea textArea;
    private static final int serverport = 4321;
    private static ServerSocket server;

    public RecebidorDeMensagens(JTextArea textArea) throws IOException {
        if (server == null) {
            this.server = new ServerSocket(serverport);
        }
        RecebidorDeMensagens.textArea = textArea;
    }

    public void esperandoReceber() {
        try {
            Socket client = server.accept();
            System.out.println("Cliente " + client.getInetAddress().getHostAddress() + " conectado");
            BufferedReader leitor = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String mensagem = leitor.readLine();
            System.out.println("Mensagem do cliente " + mensagem);
            textArea.setText(textArea.getText() + mensagem + " " + client.getInetAddress().getHostAddress() + "\n");
            Socket conection = new Socket(client.getInetAddress().getHostAddress(), 4321);
            PrintWriter escritor = new PrintWriter(conection.getOutputStream(), true);
            escritor.println(mensagem);
            client.close();
        } catch (Exception e) {
            System.out.println("erro ai");
        }
    }

    @Override
    public void run() {
        System.out.println("Recebendo na porta: " + server.getLocalPort());
        while (true) {
            esperandoReceber();
        }
    }

}
