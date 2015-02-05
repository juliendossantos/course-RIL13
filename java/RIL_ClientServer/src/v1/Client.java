package v1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client 
{
	public Client(String serveur) 
	{
		try
		{
			// Création du socket de connection
			Socket socket = new Socket(serveur, 1234);
			// Flux de lecture
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String recept = in.readLine();
			System.out.println(recept);
			socket.close();
		} catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new Client("localhost");
	}
}
