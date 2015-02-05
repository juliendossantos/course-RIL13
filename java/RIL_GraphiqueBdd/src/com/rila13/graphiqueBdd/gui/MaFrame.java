package com.rila13.graphiqueBdd.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import com.rila13.graphiqueBdd.bin.Acteurs;

@SuppressWarnings("serial")
public class MaFrame extends JFrame
{
	private ContentPanel panelContenu;
	private PhotoPanel panelPhoto;
	private ButtonsPanel panelButtons;
	private Acteurs acteurs = new Acteurs();

	public MaFrame()
	{
		// modif du titre et de la taille
		setTitle("Mon Applicatio GUI");
		setSize(800, 530);
		setLayout(new BorderLayout(10, 10));
		
		panelContenu = new ContentPanel(this);
		panelPhoto=new PhotoPanel(this);
		panelButtons = new ButtonsPanel(this);
		
		// ajout des panels
		add(panelContenu);
		add(panelButtons,BorderLayout.SOUTH);
		add(panelPhoto,BorderLayout.EAST);
		
	}

	public ContentPanel getPanelContenu()
	{
		return panelContenu;
	}

	public PhotoPanel getPanelPhoto()
	{
		return panelPhoto;
	}

	public ButtonsPanel getPanelButtons() {
		return panelButtons;
	}

	public Acteurs getActeurs() {
		return acteurs;
	}
}
