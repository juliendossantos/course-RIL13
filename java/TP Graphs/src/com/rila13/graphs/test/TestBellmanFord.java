package com.rila13.graphs.test;

import com.rila13.graphs.beans.BellmanFordEngine;
import com.rila13.graphs.beans.Node;
import com.rila13.graphs.beans.TypeOptim;

public class TestBellmanFord {

	public static void main(String[] args) {
		Node[] nodes = {
				new Node("Arles"),
				new Node("Marseille Entrée"),
				new Node("Marseille sortie"),
				new Node("Aix Entrée"),
				new Node("Aix sortie"),
				new Node("Toulon"),
		};
		
		final float valeurs[][] = {
				{0,3,85,110,4.3F},
				{3,4,15,90,0},
				{4,5,55,110,4.9F},
				{0,1,75,110,0},
				{1,2,13,50,2},
				{2,5,64,110,3}
		};
		// initialisation
		for(int i = 0; i<valeurs.length; i++)
		{
			float iNSource =valeurs[i][0]; //indice node source
			float iNDest = valeurs[i][1]; //indice node destination
			float distance=valeurs[i][2]; //valeur de l'arc
			float vitesse=valeurs[i][3]; //valeur de l'arc
			float peage=valeurs[i][4]; //valeur de l'arc
			nodes[(int)iNSource].ajouteArc(distance,vitesse,peage, nodes[(int)iNDest]);
		}
		
		BellmanFordEngine engine = new BellmanFordEngine(nodes);
		engine.init();		
		engine.startEngine(TypeOptim.distance);
		engine.initTrajet();
		System.out.println("# Le plus court trajet");
		System.out.println("-----------------");
		System.out.println(engine.getTrajet().outPut());

		System.out.println("-----------------");
		engine.init();		
		engine.startEngine(TypeOptim.temps);
		engine.initTrajet();		
		System.out.println("# Le plus rapide");
		System.out.println("-----------------");
		System.out.println(engine.getTrajet().outPut());

		System.out.println("-----------------");
		engine.init();		
		engine.startEngine(TypeOptim.cout);
		engine.initTrajet();		
		System.out.println("# Le moins cher");
		System.out.println("-----------------");
		System.out.println(engine.getTrajet().outPut());
		
		System.out.println("-----------------");		
		engine.init();		
		engine.startEngine(TypeOptim.gazole);
		engine.initTrajet();	
		System.out.println("# Le moins polluant");
		System.out.println("-----------------");
		System.out.println(engine.getTrajet().outPut());

	}

}
