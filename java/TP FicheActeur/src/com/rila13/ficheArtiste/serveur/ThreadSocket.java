package com.rila13.ficheArtiste.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;

import com.rila13.ficheArtiste.serveur.bin.Acteur;
import com.rila13.ficheArtiste.serveur.bin.Acteurs;

public class ThreadSocket implements Runnable {

	private Socket socket;
	static private Acteurs acteurs = new Acteurs();

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
			Boolean result = true;

			while(result) {
				String recept = in.readLine();

				try
				{
					if(recept.equals("acteurs")) {
						out.println(acteurs.getJSONArray().toString());
					} else {
						Pattern p = Pattern.compile("^acteurs/([1-9]+)$");
						Matcher m = p.matcher(recept);
						System.out.println("ok");
						if(m.find()) {

							int requestId = new Integer(m.group(1));
							System.out.println(""+requestId);
							for(int i = 0; i < acteurs.getSize();i++)
							{
								Acteur acteur = acteurs.getActeur(i);
								int id = acteur.getCodeActeur();
								if(id == requestId) {
									out.println(acteur.toJSONObject().toString());
									break;
								}
							}

						} else {
							result = false;
						}
					}
				} catch(JSONException e) 
				{
					System.err.println(e.getMessage());
				}
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

