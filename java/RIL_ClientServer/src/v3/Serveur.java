package v3;

import java.io.IOException;
import java.net.ServerSocket;

public class Serveur {

	public Serveur() {
		try 
		{
			@SuppressWarnings("resource")
			ServerSocket ssock = new ServerSocket(1234);
			System.out.println("Le serveur est à l'écoute");
			while(true)
			{
				// Déclaration et lancement du thread socket
				ThreadSocket threadSocket = new ThreadSocket(ssock.accept());
				new Thread(threadSocket).start(); // identique à : new Thread(new ThreadEspion(this)).start();
			}
		} catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Serveur();
	}

}
