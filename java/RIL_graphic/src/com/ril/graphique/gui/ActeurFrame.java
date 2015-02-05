package com.ril.graphique.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ActeurFrame extends JFrame {

	private static PanelContent content;
	private static PanelButton button;

	public ActeurFrame() {
		// Modification du titre et de la taille
		setTitle("Acteurs");
		setSize(600, 600);

		// Création des panels
		content = new PanelContent(this);
		button = new PanelButton(this);

		// Ajout des panels
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(content,gbc);
		
		gbc.weightx = 0.25;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(button,gbc);
		
	}

	public static PanelContent getContent() {
		return content;
	}

	public static PanelButton getButton() {
		return button;
	}

}
