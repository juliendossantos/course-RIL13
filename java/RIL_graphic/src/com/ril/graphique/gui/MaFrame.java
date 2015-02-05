package com.ril.graphique.gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MaFrame extends JFrame {
	
	public MaFrame() {
		// Création d'un panel
		MonPanel panel = new MonPanel(this);
		// Modification du titre et de la taille
		setTitle("Mon application GUI");
		setSize(400, 400);
		// Ajout du panel
		add(panel);
		
	}

}