package com.rila13.test;

import com.rila13.graphs.beans.BellmanFordEngine;
import com.rila13.graphs.beans.Node;

public class TestBellmanFord {

	public static void main(String[] args) {
		Node[] nodes = {
				new Node("A"),
				new Node("B"),
				new Node("C"),
				new Node("D"),
				new Node("E"),
				new Node("F"),
				new Node("G"),
				new Node("H")
		};
		
		final int valeurs[][] = {
				{0,1,1},
				{0,4,9},
				{1,2,1},
				{2,6,1},
				{6,4,1},
				{4,5,2},
				{5,3,1},
				{3,7,1}
		};
		// initialisation
		for(int i =0; i<valeurs.length; i++)
		{
			int iNSource =valeurs[i][0]; //indice node source
			int iNDest = valeurs[i][1]; //indice node destination
			int valeur=valeurs[i][2]; //valeur de l'arc
			nodes[iNSource].ajouteArc(valeur, nodes[iNDest]);
		}

		BellmanFordEngine engine = new BellmanFordEngine(nodes);
		engine.init();
		engine.startEngine();

	}

}
