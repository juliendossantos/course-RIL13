package com.rila13.ficheArtiste.client.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class ContentPanel extends JPanel
{
	private MaFrame frame;
	private JSONArray acteurs;
	private JTextField jtfId, jtfPrenom, jtfNom, jtfAnneeNaissance;
	JRadioButton jrbFemale,jrbMale;

	public ContentPanel(MaFrame frame)
	{
		try
		{
			this.frame = frame;
			// un encadré avec une légende
			setBorder(BorderFactory.createTitledBorder("Données de l'acteur"));
			// paramétrage gridbaglayout
			setLayout(new GridBagLayout());
			GridBagConstraints gbc=new GridBagConstraints();
			gbc.anchor=GridBagConstraints.LINE_START;
			gbc.insets=new Insets(5, 5, 5, 5);

			// Récupération de la liste des acteurs
			acteurs = new JSONArray(this.frame.request("acteurs"));			

			// Création / Ajout des composants
			JComboBox<JSONObject> comboBox = new JComboBox<JSONObject>();
			ComboBoxRenderer renderer = new ComboBoxRenderer();
			comboBox.setRenderer(renderer);
			for(int i = 0; i < acteurs.length(); i++)
			{
				JSONObject acteur = (JSONObject) acteurs.get(i);
				comboBox.addItem(acteur);
			}
			ajouteComposant(comboBox, gbc, 1, 0, 2);

			ajouteComposant(new JLabel("Prénom :"), gbc, 2, 0, 1);
			ajouteComposant(jtfPrenom=new JTextField(20), gbc, 2, 1, 2);

			ajouteComposant(new JLabel("Nom :"), gbc, 3, 0, 1);
			ajouteComposant(jtfNom=new JTextField(20), gbc, 3, 1, 2);

			ajouteComposant(new JLabel("Sexe :"), gbc, 4, 0,1);

			ajouteComposant(jrbFemale=new JRadioButton("Féminin"), gbc, 4, 1, 1);
			ajouteComposant(jrbMale=new JRadioButton("Masculin"), gbc, 4, 2, 1);


			ajouteComposant(new JLabel("Né(e) en :"), gbc, 5, 0, 1);
			ajouteComposant(jtfAnneeNaissance=new JTextField(5), gbc, 5, 1, 2);
			comboBox.addActionListener(new ComboBoxListener(this));

			// on groupe les boutons radio --> un seul cliqué à la fois
			ButtonGroup group=new ButtonGroup();
			group.add(jrbFemale);
			group.add(jrbMale);
		} catch(IOException e)
		{
			System.err.println(e.getMessage());
		} catch(JSONException e)
		{
			System.err.println(e.getMessage());
		}
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

	public MaFrame getFrame() {
		return frame;
	}

}
