package com.rila13.graphiqueBdd.gui;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.rila13.graphiqueBdd.bin.Acteurs;
import com.rila13.graphiqueBdd.listeners.MonEcouteur;
import com.rila13.utilities.ManipMedia;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel
{
	private MaFrame frame;
	private JButton jbPremier,jbPrecedent,jbSuivant,jbDernier,jbQuitte;

	public ButtonsPanel(MaFrame frame)
	{
		this.frame = frame;
		Acteurs acteurs = frame.getActeurs();

		setLayout(new GridLayout(1, 5, 10, 10));
		add(jbPremier=new JButton(ManipMedia.vaChercher("images/Rfirst.png")));
		add(jbPrecedent=new JButton(ManipMedia.vaChercher("images/Rprevious.png")));
		add(jbSuivant=new JButton(ManipMedia.vaChercher("images/Rnext.png")));
		add(jbDernier=new JButton(ManipMedia.vaChercher("images/Rlast.png")));
		add(jbQuitte=new JButton(ManipMedia.vaChercher("images/Rquit.png")));
		if(acteurs.isFirst()) {
			jbPremier.setIcon(ManipMedia.vaChercher("images/Dfirst.png"));
			jbPremier.setEnabled(false);
			jbPrecedent.setIcon(ManipMedia.vaChercher("images/Dprevious.png"));
			jbPrecedent.setEnabled(false);
		}
		// les listeners
		MonEcouteur ecouteur=new MonEcouteur(this);
		jbPremier.addActionListener(ecouteur);
		jbPrecedent.addActionListener(ecouteur);
		jbSuivant.addActionListener(ecouteur);
		jbDernier.addActionListener(ecouteur);
		jbQuitte.addActionListener(ecouteur);
	}
	// les getters des boutons

	public MaFrame getFrame()
	{
		return frame;
	}

	public void setFrame(MaFrame frame)
	{
		this.frame = frame;
	}

	public JButton getJbPremier()
	{
		return jbPremier;
	}

	public JButton getJbPrecedent()
	{
		return jbPrecedent;
	}

	public JButton getJbSuivant()
	{
		return jbSuivant;
	}

	public JButton getJbDernier()
	{
		return jbDernier;
	}

	public JButton getJbQuitte()
	{
		return jbQuitte;
	}
	
}
