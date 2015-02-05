package v1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	
	public Serveur() {
		try 
		{
			ServerSocket ssock = new ServerSocket(1234);
			System.out.println("Le serveur est à l'écoute");
			
			Socket socket = ssock.accept();
			System.out.println("J'ai reçus une connection");
			// Flux d'écriture sur le socket
			OutputStream ostream = socket.getOutputStream();
			// On encapsule ce flux dans un printWriter
			PrintWriter out = new PrintWriter(ostream, true);
			// On envoie un message au client
			out.println("Hello depuis le serveur");
			ssock.close();
		} catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Serveur();
	}

}
