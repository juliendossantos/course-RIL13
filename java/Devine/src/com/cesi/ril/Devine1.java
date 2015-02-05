package com.cesi.ril;

import java.util.Random;
import java.util.Scanner;

public class Devine1 {
	
	public Devine1() {
		Random random = new Random();
		Scanner in = new Scanner(System.in);
		int nombreATrouver = random.nextInt(100+1);
		System.out.println("Trouver un nombre entre 1 et 100");
		int nbCoups = 0;
		while(true) {
			nbCoups++;
			System.out.print("Donner votre choix : ");
			int nbUser = in.nextInt();
			if(nombreATrouver == nbUser)
				break;
			else if (nombreATrouver > nbUser)
				System.out.println("Trop petit !");
			else if (nombreATrouver < nbUser)
				System.out.println("Trop grand !");
		}
		System.out.println("Trouver en " + nbCoups + " coups.");
	}
	
	public static void main(String[] args) {
		new Devine1();
	}

}
