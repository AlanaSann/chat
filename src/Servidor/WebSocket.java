package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebSocket {

	    private static final int serverport = 8080;
	    private static ServerSocket server;
	    
	    WebSocket() throws IOException{
	    	this.server =new ServerSocket(serverport);
	    }

	    public static void main(String[] args) throws Exception {
	       
	        System.out.println("Servidor na porta: " + server.getLocalPort());
	        while (true) {
	            Socket client = server.accept();
	            Requests requests = new Requests(client);
	            requests.run();
	     }
	}
}

