package com.rila13.graphiqueBdd.tests;

import com.rila13.graphiqueBdd.bin.Acteurs;

public class TestActeurs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Acteurs acteurs = new Acteurs();
		
		for (; acteurs.current < acteurs.getSize(); acteurs.next()) {
			System.out.println(acteurs.getActeurCurrent().toString());
		}
	}

}
