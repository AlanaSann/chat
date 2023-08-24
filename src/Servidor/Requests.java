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
            System.out.println(clients.size());
            enviaMensagensParaTodosOsUsuariosDaLista(mensagem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviaMensagensParaTodosOsUsuariosDaLista(String mensagem) {
        for (Map.Entry<String, Socket> pair : clients.entrySet()) {
            enviaMensagensParaUmUsuario(mensagem, pair.getValue());
        }
    }

    public void enviaMensagensParaUmUsuario(String mensagem, Socket socketDeUmUsuario) {
        try (Socket conection = new Socket(socketDeUmUsuario.getInetAddress().getHostAddress(), 4321);) {
            PrintWriter escritor = new PrintWriter(conection.getOutputStream(), true);
            System.out.println(
                    "Reenviando mensagem: " + mensagem + " para" + socketDeUmUsuario.getInetAddress());
            escritor.println(mensagem);
        } catch (Exception e) {
            System.out.println(clients.size());
            clients.remove(socketDeUmUsuario.getInetAddress().getCanonicalHostName());
            System.out.println(clients.size());
        }
    }
}