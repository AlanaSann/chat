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

    public Requests(Socket client) throws SocketException {
        if (clients == null) {
            Requests.clients = new HashMap<String, Socket>();
        }
        if (!clients.containsKey(client.getInetAddress().getCanonicalHostName())) {
            client.setKeepAlive(true);
            clients.put(client.getInetAddress().getCanonicalHostName(), client);
        }
        remetente = client;
    }

    @Override
    public void run() {
        // System.out.println("Cliente " + remetente.getInetAddress().getHostAddress() +
        // " conectado");
        try (BufferedReader leitor = new BufferedReader(new InputStreamReader(remetente.getInputStream()))) {
            // PrintWriter escritor = new PrintWriter(remetente.getOutputStream(), true);
            String mensagem = leitor.readLine();
            // System.out.println("Mensagem do cliente " + mensagem);
            for (Map.Entry<String, Socket> pair : clients.entrySet()) {
                Socket conection = new Socket(pair.getValue().getInetAddress().getHostAddress(), 4321);
                PrintWriter escritor = new PrintWriter(conection.getOutputStream(), true);
                escritor.println(mensagem);
            }
            System.out.println(clients.size());
            // escritor.println("A mensagem: " + mensagem + ", foi recebida");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}