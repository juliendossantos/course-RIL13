package com.rila13.graphiqueBdd.lanceurs;

import javax.swing.JFrame;

import com.rila13.graphiqueBdd.gui.MaFrame;

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
