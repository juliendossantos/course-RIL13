package com.rila13.graphiqueBdd.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import com.rila13.graphiqueBdd.bin.Acteurs;
import com.rila13.graphiqueBdd.bin.genre;
import com.rila13.graphiqueBdd.gui.ButtonsPanel;
import com.rila13.graphiqueBdd.gui.ContentPanel;
import com.rila13.graphiqueBdd.gui.PhotoPanel;
import com.rila13.utilities.ManipMedia;

public class MonEcouteur implements ActionListener
{
	private ButtonsPanel panel;
	public MonEcouteur(ButtonsPanel panel)
	{
		this.panel = panel;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Acteurs acteurs = panel.getFrame().getActeurs();
		ContentPanel panelContenu = panel.getFrame().getPanelContenu();
		PhotoPanel panelPhoto = panel.getFrame().getPanelPhoto();
		ButtonsPanel panelButtons = panel.getFrame().getPanelButtons();

		if(e.getSource()==panel.getJbPrecedent())
		{
			if(acteurs.getCurrent() > 0)
				acteurs.previous();
		}
		else if(e.getSource()==panel.getJbSuivant())
		{
			if(acteurs.getCurrent() < acteurs.getSize()-1)
				acteurs.next();
		}
		else if(e.getSource()==panel.getJbPremier()) {
			acteurs.first();
		}
		else if(e.getSource()==panel.getJbDernier()) {
			acteurs.last();
		}
		else if(e.getSource()==panel.getJbQuitte())
		{
			System.exit(0);
		}

		panelContenu.getJtfId().setText("" + acteurs.getActeurCurrent().getCodeActeur());
		panelContenu.getJtfNom().setText(acteurs.getActeurCurrent().getNomActeur());
		panelContenu.getJtfPrenom().setText(acteurs.getActeurCurrent().getPrenomActeur());
		if(acteurs.getActeurCurrent().getSexeActeur() == genre.Masculin) {
			panelContenu.getJrbMale().setSelected(true);
		}
		else if(acteurs.getActeurCurrent().getSexeActeur() == genre.Feminin){
			panelContenu.getJrbFemale().setSelected(true);
		}
		panelContenu.getJtfAnneeNaissance().setText("" + acteurs.getActeurCurrent().getAnneeNaissanceActeur());
		panelPhoto.getPhoto().setIcon(new ImageIcon(acteurs.getActeurCurrent().getImage()));
		
		if(acteurs.isFirst()) {
			panelButtons.getJbPremier().setIcon(ManipMedia.vaChercher("images/Dfirst.png"));
			panelButtons.getJbPremier().setEnabled(false);
			panelButtons.getJbPrecedent().setIcon(ManipMedia.vaChercher("images/Dprevious.png"));
			panelButtons.getJbPrecedent().setEnabled(false);
			if(!panelButtons.getJbDernier().isEnabled()) {
				panelButtons.getJbDernier().setIcon(ManipMedia.vaChercher("images/Rlast.png"));
				panelButtons.getJbDernier().setEnabled(true);				
			}
			if(!panelButtons.getJbSuivant().isEnabled()) {
				panelButtons.getJbSuivant().setIcon(ManipMedia.vaChercher("images/Rnext.png"));
				panelButtons.getJbSuivant().setEnabled(true);				
			}		
		}
		else if(acteurs.isLast()) {
			panelButtons.getJbDernier().setIcon(ManipMedia.vaChercher("images/Dlast.png"));
			panelButtons.getJbDernier().setEnabled(false);
			panelButtons.getJbSuivant().setIcon(ManipMedia.vaChercher("images/Dnext.png"));
			panelButtons.getJbSuivant().setEnabled(false);
			if(!panelButtons.getJbPremier().isEnabled()) {
				panelButtons.getJbPremier().setIcon(ManipMedia.vaChercher("images/Rfirst.png"));
				panelButtons.getJbPremier().setEnabled(true);				
			}
			if(!panelButtons.getJbPrecedent().isEnabled()) {
				panelButtons.getJbPrecedent().setIcon(ManipMedia.vaChercher("images/Rprevious.png"));
				panelButtons.getJbPrecedent().setEnabled(true);				
			}	
		}
		else {
			panelButtons.getJbPremier().setIcon(ManipMedia.vaChercher("images/Rfirst.png"));
			panelButtons.getJbPremier().setEnabled(true);
			panelButtons.getJbPrecedent().setIcon(ManipMedia.vaChercher("images/Rprevious.png"));
			panelButtons.getJbPrecedent().setEnabled(true);
			panelButtons.getJbDernier().setIcon(ManipMedia.vaChercher("images/Rlast.png"));
			panelButtons.getJbDernier().setEnabled(true);
			panelButtons.getJbSuivant().setIcon(ManipMedia.vaChercher("images/Rnext.png"));
			panelButtons.getJbSuivant().setEnabled(true);			
		}
	}

}
