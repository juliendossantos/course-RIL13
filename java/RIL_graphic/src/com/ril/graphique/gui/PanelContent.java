package com.ril.graphique.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelContent extends JPanel {

	private JTextField jtf1;
	private JTextField jtf2;

	public PanelContent(ActeurFrame frame) {
		// layout de type cellule
		setSize(600, 400);
		setAlignmentX(0);
		setAlignmentY(0);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// labels
		gbc.weightx = 1;
		gbc.weighty = 0.75;
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel("Label 1"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;		
		jtf1 = new JTextField(20);
		add(jtf1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(new JLabel("Label 1"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		jtf2 = new JTextField(20);
		add(jtf2,gbc);

	}

	public JTextField getJtf1() {
		return jtf1;
	}

	public JTextField getJtf2() {
		return jtf2;
	}
}
