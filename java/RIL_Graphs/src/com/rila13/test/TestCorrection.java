package com.rila13.test;

import com.rila13.graphs.beans.Arc;
import com.rila13.graphs.beans.Node;

public class TestCorrection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node[] nodes = {
				new Node("A"),
				new Node("B"),
				new Node("C"),
				new Node("D"),
				new Node("E"),
				new Node("F")
			};
		
		final int valeurs[][] = {
				{0,1,5}/*A à B valeur 5*/,
				{0,2,3},
				{1,3,7},
				{1,4,2},
				{2,4,5},
				{3,5,1},
				{4,5,1}
		};
		// initialisation
		for(int i =0; i<valeurs.length; i++)
		{
			int iNSource =valeurs[i][0]; //indice node source
			int iNDest = valeurs[i][1]; //indice node destination
			int valeur=valeurs[i][2]; //valeur de l'arc
			nodes[iNSource].ajouteArc(valeur, nodes[iNDest]);
		}
		
		for(int iSource = 0;iSource<nodes.length;iSource++)
		{
			for(Arc arc:nodes[iSource].getArcs())
			{
				System.out.println("Arc de " + nodes[iSource].getLibelle() + " à " + arc.getDest().getLibelle() + " avec une valeur de " + arc.getValeur());
			}
		}
		
		System.out.println("---------------------------------------------");

		for(int iSource = 0;iSource<nodes.length;iSource++)
		{
			for(int iDest=0;iDest<nodes.length;iDest++)
			{
				if(nodes[iSource].getLibelle() == nodes[iDest].getLibelle())
					continue;

				System.out.println("Depuis " + nodes[iSource].getLibelle() + " :");
				int cheminDirect = nodes[iSource].existeCheminDirect(nodes[iDest]); 
				
				if(cheminDirect != -1)
					System.out.println("- Arc direct vers " + nodes[iDest].getLibelle() + " avec une valeur de " + cheminDirect);
				else
					System.out.println("- Chemin indirect vers " + nodes[iDest].getLibelle());
			}
		}
	}

}
