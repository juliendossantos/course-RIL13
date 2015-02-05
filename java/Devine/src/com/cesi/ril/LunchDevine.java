package com.cesi.ril;

import java.util.Scanner;

public class LunchDevine {
	
	public LunchDevine() {
	
		Scanner in = new Scanner(System.in);
		Devine devine = new Devine();
		
		while(true) {
			
			devine.nouveauJeu();
			System.out.println("Trouver un nombre entre 1 et 100");
			
			while(!devine.isFini()) {
				System.out.print("Donner votre choix : ");
				TypeReponse proposition = devine.proposerNombre(in.nextInt());
				if (proposition.equals(TypeReponse.TROP_PETIT))
					System.out.println("Trop petit !");
				else if (proposition.equals(TypeReponse.TROP_GRAND))
					System.out.println("Trop grand !");
			}
			System.out.println("Trouver en " + devine.getNbCoups() + " coups.");
			System.out.println("Vous souhaitez rejouer ? (Y|N)");
			
			if(in.next().equals("N"))
				break;
		
		}
		in.close();
	}
	
	public static void main(String[] args) {
		new LunchDevine();
	}

}
