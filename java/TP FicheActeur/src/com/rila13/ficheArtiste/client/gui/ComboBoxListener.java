package com.rila13.ficheArtiste.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import org.json.JSONException;
import org.json.JSONObject;

public class ComboBoxListener implements ActionListener {

	private ContentPanel panel;

	public ComboBoxListener(ContentPanel panel) {
		this.panel = panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JSONObject acteurSelected = (JSONObject)((JComboBox)e.getSource()).getSelectedItem();
		try {
			JSONObject acteur = new JSONObject(panel.getFrame().request("acteurs/"+acteurSelected.getString("id")));
			
			panel.getJtfPrenom().setText(acteur.getString("prenom"));
			panel.getJtfNom().setText(acteur.getString("nom"));
			panel.getJtfAnneeNaissance().setText(acteur.getString("annee de naissance"));
			if(acteur.getString("sexe").equals("Masculin")) 
			{
				panel.getJrbMale().setSelected(true);
			} else if(acteur.getString("sexe").equals("Feminin"))
			{
				panel.getJrbFemale().setSelected(true);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
