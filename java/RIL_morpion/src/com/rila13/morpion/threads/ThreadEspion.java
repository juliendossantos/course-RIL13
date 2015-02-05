package com.rila13.morpion.threads;

import javax.swing.JOptionPane;

import com.rila13.morpion.gui.ButtonsPanel;

public class ThreadEspion implements Runnable {
	// champ privé qui contenant l'adresse de l'objet Test
		private ButtonsPanel papa;

		public ThreadEspion(ButtonsPanel papa) {
			this.papa = papa;
		}

		@Override
		public void run() {
			//Boucle infini
			while(true) {
				// dormir 200 milliseconde
				try {
					Thread.sleep(200);
				} catch(InterruptedException e) {
				}

				if(papa.getNbO() == 4) {
					JOptionPane.showMessageDialog(papa, "Vous avez gagné!!!");
					System.exit(0);
				}

			}
		}
}
