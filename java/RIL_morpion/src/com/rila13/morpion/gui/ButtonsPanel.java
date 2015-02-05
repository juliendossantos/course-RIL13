package com.rila13.morpion.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.rila13.morpion.threads.ThreadEspion;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel implements ActionListener {
	
	static private int TAILLE=4;
	private Frame frame;
	private int nbO=0;
	private JButton[][] buttons = new JButton[TAILLE][TAILLE];

	public ButtonsPanel(Frame frame)
	{
		this.frame = frame;
		ThreadEspion espion = new ThreadEspion(this);
		new Thread(espion).start(); // identique à : new Thread(new ThreadEspion(this)).start();

		setLayout(new GridLayout(TAILLE, TAILLE, 5, 5));
		for(int i=0;i<TAILLE;i++) {
			for(int j=0;j<TAILLE;j++) {
				add(buttons[i][j] = new JButton("-"));
				buttons[i][j].addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// on récupére le JButton qui nous a appelé
		JButton button =(JButton) e.getSource();
		if(button.getText()=="-"){
			button.setText("O");
			nbO++;
		} else {
			button.setText("-");
			nbO--;
		}
		frame.setTitle("Nombre de O : "+nbO);
	}

	public int getNbO() {
		return nbO;
	}
}
