package com.ril.graphique.ecouteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.ril.graphique.gui.MonPanel;

public class MonEcouteur implements ActionListener {

	private MonPanel panel;

	public MonEcouteur(MonPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panel.getJbCal()) {
			System.out.println("Calcule");
			try {
				int A = Integer.parseInt(panel.getJtfA().getText());
				int B = Integer.parseInt(panel.getJtfB().getText());
				int C = A*B;
				JOptionPane.showMessageDialog(panel, String.format("%d * %d = %d", A,B,C));
			} catch(Exception excep) {
				System.err.println(excep.getMessage());
				JOptionPane.showMessageDialog(panel, "vérifier le format");
			}
			
		} else if(e.getSource() == panel.getJbQuitte()) {
			System.exit(0);
		}
	}

}