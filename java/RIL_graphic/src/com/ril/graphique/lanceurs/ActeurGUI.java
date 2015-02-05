package com.ril.graphique.lanceurs;

import javax.swing.JFrame;

import com.ril.graphique.gui.ActeurFrame;

public class ActeurGUI {

	public static void main(String[] args) {
		// instancie l'objet frame
		ActeurFrame frame = new ActeurFrame();
		// réaction a la fermeture
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// on la rend visible
		frame.setVisible(true);
	}

}
