package com.rila13.ficheArtiste.client.gui;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

import org.json.JSONException;
import org.json.JSONObject;

import com.rila13.ficheArtiste.serveur.bin.Acteurs;

public class MaFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContentPanel panelContenu;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	public MaFrame()
	{
		// modif du titre et de la taille
		setTitle("Fiche artiste");
		setSize(400, 400);
		setLayout(new BorderLayout(10, 10));
		
		startSocket("localhost");
		panelContenu = new ContentPanel(this);
		add(panelContenu);
	}
	
	public void startSocket(String serveur) {
		try
		{
			// Création du socket de connection
			socket = new Socket(serveur, 1234);
			// Flux d'écriture sur le socket
			OutputStream ostream = socket.getOutputStream();
			out = new PrintWriter(ostream, true);
			// Flux de lecture sur le socket
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			in = new BufferedReader(isr);

		} catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
	}
	
	public String request(String requete) throws IOException, JSONException {
		out.println(requete);
		String recept = in.readLine();
		
		if(recept.equals("Tchao"))
		{
			System.out.println("Le serveur a fermé la connection");
			closeSocket();
			return null;
		}

		return recept;
	}
	
	public void closeSocket() throws IOException {
		System.out.println("Connection fermé");
		socket.close();	
	}
}
