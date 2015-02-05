package com.rila13.graphiqueBdd.gui;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rila13.graphiqueBdd.bin.Acteurs;

@SuppressWarnings("serial")
public class PhotoPanel extends JPanel
{
	private MaFrame frame;
	private JLabel photo;

	public PhotoPanel(MaFrame frame)
	{
		this.frame = frame;
		Acteurs acteurs = this.frame.getActeurs();
		setBorder(BorderFactory.createTitledBorder("Photo"));
		add(photo=new JLabel(new ImageIcon("images/unknown.jpg")));
		photo.setIcon(new ImageIcon(acteurs.getActeurCurrent().getImage()));
	}

	public JLabel getPhoto()
	{
		return photo;
	}
	
}
