package v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	static private String responses[] = new String[]{
		"lave le linge!",
		"fait la vaiselle",
		"passe le balais"
	};

	public Serveur() {
		try 
		{
			ServerSocket ssock = new ServerSocket(1234);
			System.out.println("Le serveur est à l'écoute");
			Socket socket = ssock.accept();
			System.out.println("J'ai reçus une connection");			
			// Flux de lecture du socket
			InputStream istream = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(istream);
			BufferedReader in = new BufferedReader(isr);				
			// Flux d'écriture sur le socket
			OutputStream ostream = socket.getOutputStream();
			// On encapsule ce flux dans un printWriter
			PrintWriter out = new PrintWriter(ostream, true);

			while(true) {
				int recept = new Integer(in.readLine());
				int length = responses.length;

				if(!(recept < length))
					break;

				out.println(responses[recept].toString());
			}
			out.println("Tchao");
			System.out.println("Fermeture de la connexion au client");
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
