package com.ril.graphique.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.ril.graphique.ecouteur.ButtonEcouteur;

@SuppressWarnings("serial")
public class PanelButton extends JPanel {

	private ActeurFrame frame;
	private JButton jbPrev;
	private JButton jbNext;
	private JButton jbFirst;
	private JButton jbLast;
	
	public PanelButton(ActeurFrame frame) {
		
		this.frame = frame;

		// layout de type cellule
		setSize(600, 200);
		setAlignmentX(0);
		setAlignmentY(400);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weightx = 1;
		gbc.weighty = 0.30;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		jbPrev = new JButton("Prev");
		add(jbPrev,gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		jbNext = new JButton("Next");
		add(jbNext,gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		jbFirst = new JButton("First");
		add(jbFirst,gbc);

		gbc.gridx = 3;
		gbc.gridy = 0;
		jbLast = new JButton("Last");
		add(jbLast,gbc);

		// instance de l'écouteur des deux boutons
		ButtonEcouteur ecouteur = new ButtonEcouteur(this);
		jbPrev.addActionListener(ecouteur);
		jbNext.addActionListener(ecouteur);
		jbFirst.addActionListener(ecouteur);
		jbLast.addActionListener(ecouteur);
	}

	public ActeurFrame getFrame() {
		return frame;
	}

	public JButton getJbPrev() {
		return jbPrev;
	}

	public JButton getJbNext() {
		return jbNext;
	}

	public JButton getJbFirst() {
		return jbFirst;
	}

	public JButton getJbLast() {
		return jbLast;
	}
}
