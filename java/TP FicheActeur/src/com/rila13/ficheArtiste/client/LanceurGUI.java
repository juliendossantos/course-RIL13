package com.rila13.ficheArtiste.client;

import javax.swing.JFrame;

import com.rila13.ficheArtiste.client.gui.MaFrame;

public class LanceurGUI
{
	public static void main(String[] args)
	{
		// instancie l'objet frame
		MaFrame frame=new MaFrame();
		// réaction à la fermeture
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// on la rend visible
		frame.setVisible(true);
	}

}
