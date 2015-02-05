package v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadSocket implements Runnable {

	private Socket socket;
	static private String responses[] = new String[]{
		"lave le linge!",
		"fait la vaiselle",
		"passe le balais"
	};

	public ThreadSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try
		{
			System.out.println("J'ai reçus une connection");			
			// Flux de lecture et écriture du socket
			InputStream istream = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(istream);
			BufferedReader in = new BufferedReader(isr);				
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
			socket.close();
		} catch(IOException e) 
		{
			System.err.println(e.getMessage());
		}
	}
}

