package com.rila13.graphiqueBdd.bin;

public class Acteur {

	private int codeActeur;
	private String prenomActeur;
	private String nomActeur;
	private genre sexeActeur;
	private int anneeNaissanceActeur;
	private byte[] image;
	
	public Acteur() {

	}
	
	public Acteur(int codeActeur, String prenomActeur, String nomActeur, genre sexeActeur, int anneeNaissanceActeur, byte[] image) {
		this.codeActeur = codeActeur;
		this.prenomActeur = prenomActeur;
		this.nomActeur = nomActeur;
		this.sexeActeur = sexeActeur;
		this.anneeNaissanceActeur = anneeNaissanceActeur;
		this.image = image;
	}

	public String toString() {
		String titre = sexeActeur==genre.Feminin ? "Mme":"M.";
		return titre + " " + prenomActeur + " " + nomActeur + " (" + anneeNaissanceActeur + ")";
	}

	public int getCodeActeur() {
		return codeActeur;
	}

	public void setCodeActeur(int codeActeur) {
		this.codeActeur = codeActeur;
	}

	public String getPrenomActeur() {
		return prenomActeur;
	}

	public void setPrenomActeur(String prenomActeur) {
		this.prenomActeur = prenomActeur;
	}

	public String getNomActeur() {
		return nomActeur;
	}

	public void setNomActeur(String nomActeur) {
		this.nomActeur = nomActeur;
	}

	public genre getSexeActeur() {
		return sexeActeur;
	}

	public void setSexeActeur(genre sexeActeur) {
		this.sexeActeur = sexeActeur;
	}

	public int getAnneeNaissanceActeur() {
		return anneeNaissanceActeur;
	}

	public void setAnneeNaissanceActeur(int anneeNaissanceActeur) {
		this.anneeNaissanceActeur = anneeNaissanceActeur;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
