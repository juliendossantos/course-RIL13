package com.rila13.morpion.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	private ButtonsPanel panelButtons;

	public Frame(){
		setTitle("Morpion");
		setSize(800, 800);
		setLayout(new BorderLayout(10, 10));
		panelButtons = new ButtonsPanel(this);
		add(panelButtons);
	}
}
