package com.rila13.ficheArtiste.serveur.bin;

import java.sql.Blob;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rila.bdd.connecteurs.MysqlConn;


public class Acteurs {

	private ArrayList<Acteur> casting = new ArrayList<>();
	public int current = 0;

	public Acteurs() {
		MysqlConn conn=new MysqlConn("films");
		// appel de la méthode select -> ResultSet
		ResultSet resultat = conn.select("acteurs");
		try {
			while(resultat.next()){
				Acteur acteur = new Acteur();
				acteur.setCodeActeur(resultat.getInt("codeActeur"));
				acteur.setNomActeur(resultat.getString("nomActeur"));
				acteur.setPrenomActeur(resultat.getString("prenomActeur"));
				acteur.setSexeActeur(resultat.getInt("sexeActeur") == 0 ? genre.Masculin : genre.Feminin);
				acteur.setAnneeNaissanceActeur(resultat.getInt("anneeNaissanceActeur"));
				Blob image = resultat.getBlob("image");
				byte[] byteImage = image.getBytes(1, (int) image.length());
				acteur.setImage(byteImage);
				casting.add(acteur);
			}
		} catch( Exception e ) {
			System.err.println(e.getMessage());
		}
	}

	public JSONArray getJSONArray() {
		try {
			JSONArray acteursArray = new JSONArray();
			for(int i = 0; i < this.getSize();i++)
			{
				acteursArray.put(this.getActeur(i).toJSONObject());
			}
			return acteursArray;

		} catch(JSONException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public Acteur getActeur(int index) {
		return casting.get(index);
	}

	public Acteur getActeurCurrent() {
		return casting.get(current);
	}

	public void first() {
		current = 0;
	}

	public void last() {
		current = casting.size()-1;
	}

	public void previous() {
		current--;
	}

	public void next() {
		current++;
	}

	public int getCurrent() {
		return current;
	}

	public int getSize() {
		return casting.size();
	}

	public boolean isFirst() {
		return current == 0;
	}

	public boolean isLast() {
		return current == casting.size()-1;
	}

}