package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

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
        try (BufferedReader leitor = new BufferedReader(new InputStreamReader(remetente.getInputStream()))) {
            String mensagem = leitor.readLine();
            for (Map.Entry<String, Socket> pair : clients.entrySet()) {
                try (Socket conection = new Socket(pair.getValue().getInetAddress().getHostAddress(), 4321);) {
                    PrintWriter escritor = new PrintWriter(conection.getOutputStream(), true);
                    System.out.println("Reenviando mensagem: " + mensagem + " para" + pair.getValue().getInetAddress());
                    escritor.println(mensagem);
                } catch (Exception e) {
                    System.out.println(clients.size());
                    clients.remove(pair.getKey());
                    System.out.println(clients.size());
                }
            }
            System.out.println(clients.size());
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}