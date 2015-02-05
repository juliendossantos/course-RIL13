package com.cesi.ril;

import java.util.Random;

public class Devine {
	
	private static Random random;
	private int nombreATrouver;
	private int nbCoups;
	private boolean fini;
	
	public Devine() {
		if(random == null)
			random = new Random();
	}
	
	public void nouveauJeu() {
		nombreATrouver = random.nextInt(100+1);
		nbCoups = 0;
		fini = false;
	}
	
	public TypeReponse proposerNombre(int nombreProposer) {
		nbCoups++;
		if (nombreATrouver > nombreProposer)
			return TypeReponse.TROP_PETIT;
		else if (nombreATrouver < nombreProposer)
			return TypeReponse.TROP_GRAND;
		else {
			fini = true;
			return TypeReponse.EGAL;
		}
	}
	
	public boolean isFini() {
		return fini;
	}

	public int getNbCoups() {
		return nbCoups;
	}

}
