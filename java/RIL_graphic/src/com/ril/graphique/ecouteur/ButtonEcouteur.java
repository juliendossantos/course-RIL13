package com.ril.graphique.ecouteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ril.graphique.gui.PanelButton;

public class ButtonEcouteur implements ActionListener {
	
	private PanelButton panel;
	
	public ButtonEcouteur(PanelButton panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panel.getJbPrev()){
			panel.getFrame().getContent().getJtf1().setText("Prev");
		}
		else if(e.getSource() == panel.getJbNext()){
			panel.getFrame().getContent().getJtf1().setText("Next");
		} else if(e.getSource() == panel.getJbFirst()){
			panel.getFrame().getContent().getJtf1().setText("First");
		} else if(e.getSource() == panel.getJbLast()){
			panel.getFrame().getContent().getJtf1().setText("Last");
		}

	}

}
