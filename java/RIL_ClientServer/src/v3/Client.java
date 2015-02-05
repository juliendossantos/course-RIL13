package v3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
	private String requete;
	private String recept;

	public Client(String serveur) 
	{
		try
		{

			// Création du socket de connection
			Socket socket = new Socket(serveur, 1234);

			Scanner req=new Scanner(System.in);
			// Flux d'écriture sur le socket
			OutputStream ostream = socket.getOutputStream();
			PrintWriter out = new PrintWriter(ostream, true);
			// Flux de lecture
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader in = new BufferedReader(isr);

			while(true)
			{
				System.out.print("Entrez une requête : ");
				requete=req.next();
				out.println(requete);

				recept = in.readLine();
				if(recept.equals("Tchao"))
					break;

				System.out.println(recept);
			}
			System.out.println("Le serveur a fermé la connection");
			socket.close();
			req.close();

		} catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Client("localhost");
	}
}
