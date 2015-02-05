package com.ril.graphique.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ril.graphique.ecouteur.MonEcouteur;

@SuppressWarnings("serial")
public class MonPanel extends JPanel {

	private JTextField jtfA;
	private JTextField jtfB;
	private JButton jbCal;
	private JButton jbQuitte;

	public MonPanel(MaFrame frame) {
		// layout de type cellule
		setLayout(new GridLayout(3, 2, 15, 15));

		// labels
		add(new JLabel("Valeur A"));
		jtfA = new JTextField(5);
		add(jtfA);

		add(new JLabel("Valeur B"));
		jtfB = new JTextField(5);
		add(jtfB);

		jbCal = new JButton("Calcule");
		add(jbCal);
		jbQuitte = new JButton("Quitter");
		add(jbQuitte);
		
		// instance de l'écouteur des deux boutons
		MonEcouteur ecouteur = new MonEcouteur(this);
		jbCal.addActionListener(ecouteur);
		jbQuitte.addActionListener(ecouteur);
	}

	public JTextField getJtfA() {
		return jtfA;
	}

	public JTextField getJtfB() {
		return jtfB;
	}

	public JButton getJbCal() {
		return jbCal;
	}

	public JButton getJbQuitte() {
		return jbQuitte;
	}
}
