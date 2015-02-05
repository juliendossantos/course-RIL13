package com.ril.graphique.lanceurs;

import javax.swing.JFrame;
import com.ril.graphique.gui.MaFrame;

public class LanceurGUI {

	public static void main(String[] args) {
		// instancie l'objet frame
		MaFrame frame = new MaFrame();
		// réaction a la fermeture
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// on la rend visible
		frame.setVisible(true);
	}

}