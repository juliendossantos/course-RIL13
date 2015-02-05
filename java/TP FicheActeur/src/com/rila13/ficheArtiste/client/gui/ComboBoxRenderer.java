package com.rila13.ficheArtiste.client.gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.json.JSONException;
import org.json.JSONObject;

public class ComboBoxRenderer extends JLabel implements ListCellRenderer<JSONObject> {

	public ComboBoxRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(
			JList<? extends JSONObject> list, JSONObject value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
		
		try {
			setText(value.getString("prenom") + " " + value.getString("nom"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this;
	}

}
