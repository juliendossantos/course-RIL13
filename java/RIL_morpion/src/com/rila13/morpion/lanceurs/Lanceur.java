package com.rila13.morpion.lanceurs;

import javax.swing.JFrame;

import com.rila13.morpion.gui.Frame;


public class Lanceur {
	

	public static void main(String[] args)
	{
		// instancie l'objet frame
		Frame frame=new Frame();
		// réaction à la fermeture
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// on la rend visible
		frame.setVisible(true);
	}
}
