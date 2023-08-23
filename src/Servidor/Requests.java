package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;

public class Requests implements Runnable {

    private static HashMap<String, Socket> clients;
    private Socket remetente;
    private JTextArea campoDeTexto;

    public Requests(Socket client, JTextArea campoDeTexto, HashMap<String, Socket> clients) throws SocketException {
        Requests.clients = clients;
        if (!clients.containsKey(client.getInetAddress().getCanonicalHostName())) {
            client.setKeepAlive(true);
            clients.put(client.getInetAddress().getCanonicalHostName(), client);
        }
        remetente = client;
        this.campoDeTexto = campoDeTexto;
    }

    @Override
    public void run() {
        System.out.println("Cliente " + remetente.getInetAddress().getHostAddress() + " conectado");
        try (BufferedReader leitor = new BufferedReader(new InputStreamReader(remetente.getInputStream()))) {
            // PrintWriter escritor = new PrintWriter(remetente.getOutputStream(), true);
            String mensagem = leitor.readLine();
            System.out.println("Mensagem do cliente " + mensagem);
            for (Map.Entry<String, Socket> pair : clients.entrySet()) {
                System.out.println(pair.getValue().isConnected());
                // System.out.println(pair.getValue().getKeepAlive());
                PrintWriter escritor = new PrintWriter(pair.getValue().getOutputStream(), true);
                escritor.println(mensagem);
            }
            campoDeTexto.setText(mensagem + remetente.getInetAddress().getHostAddress());
            // escritor.println("A mensagem: " + mensagem + ", foi recebida");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}