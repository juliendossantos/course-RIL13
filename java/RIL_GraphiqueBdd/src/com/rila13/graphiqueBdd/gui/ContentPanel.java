package com.rila13.graphiqueBdd.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.rila13.graphiqueBdd.bin.Acteurs;
import com.rila13.graphiqueBdd.bin.genre;

@SuppressWarnings("serial")
public class ContentPanel extends JPanel
{
	private MaFrame frame;
	private JTextField jtfId, jtfPrenom, jtfNom, jtfAnneeNaissance;
	JRadioButton jrbFemale,jrbMale;

	public ContentPanel(MaFrame frame)
	{
		this.frame = frame;
		Acteurs acteurs = this.frame.getActeurs();
		// un encadré avec une légende
		setBorder(BorderFactory.createTitledBorder("Données de l'acteur"));
		// paramétrage gridbaglayout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.anchor=GridBagConstraints.LINE_START;
		gbc.insets=new Insets(5, 5, 5, 5);
		
		// Création / Ajout des composants
		ajouteComposant(new JLabel("Id :"), gbc, 0, 0, 1);
		ajouteComposant(jtfId=new JTextField(5), gbc, 0, 1, 2);
		jtfId.setText(""+acteurs.getActeurCurrent().getCodeActeur());
		
		ajouteComposant(new JLabel("Prénom :"), gbc, 1, 0, 1);
		ajouteComposant(jtfPrenom=new JTextField(20), gbc, 1, 1, 2);
		jtfPrenom.setText(acteurs.getActeurCurrent().getPrenomActeur());

		ajouteComposant(new JLabel("Nom :"), gbc, 2, 0, 1);
		ajouteComposant(jtfNom=new JTextField(20), gbc, 2, 1, 2);
		jtfNom.setText(acteurs.getActeurCurrent().getNomActeur());
		
		ajouteComposant(new JLabel("Sexe :"), gbc, 3, 0,1);

		ajouteComposant(jrbFemale=new JRadioButton("Féminin"), gbc, 3, 1, 1);
		ajouteComposant(jrbMale=new JRadioButton("Masculin"), gbc, 3, 2, 1);
		if(acteurs.getActeurCurrent().getSexeActeur() == genre.Masculin) {
			jrbMale.setSelected(true);
		}
		else if(acteurs.getActeurCurrent().getSexeActeur() == genre.Feminin){
			jrbFemale.setSelected(true);
		}

		ajouteComposant(new JLabel("Né(e) en :"), gbc, 4, 0, 1);
		ajouteComposant(jtfAnneeNaissance=new JTextField(5), gbc, 4, 1, 2);
		jtfAnneeNaissance.setText(""+acteurs.getActeurCurrent().getAnneeNaissanceActeur());
		
		// on groupe les boutons radio --> un seul cliqué à la fois
		ButtonGroup group=new ButtonGroup();
		group.add(jrbFemale);
		group.add(jrbMale);
	}
	
	/**
	 * Permet d'ajouter un composant au panel
	 * @param component (JComponent) : tout composant J... (labels, textfields, ...)
	 * @param gbc (GridBagConstraints) : pour gérer les x, y et "colspan"
	 * @param y (int) : quelle ligne
	 * @param x (int) : quelle colonne
	 * @param colspan : <>1 si fusion de plusieurs cellules, sinon 1
	 */
	private void ajouteComposant(JComponent component,GridBagConstraints gbc,int y,int x,int colspan)
	{
		gbc.gridwidth=colspan;
		gbc.gridx=x;
		gbc.gridy=y;
		add(component,gbc);
	}

	public JTextField getJtfId()
	{
		return jtfId;
	}

	public JTextField getJtfPrenom()
	{
		return jtfPrenom;
	}

	public JTextField getJtfNom()
	{
		return jtfNom;
	}

	public JTextField getJtfAnneeNaissance()
	{
		return jtfAnneeNaissance;
	}

	public JRadioButton getJrbFemale()
	{
		return jrbFemale;
	}

	public JRadioButton getJrbMale()
	{
		return jrbMale;
	}
	
}
