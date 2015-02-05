package com.rila13.test;

import java.util.ArrayList;

import com.rila13.graphs.beans.Arc;
import com.rila13.graphs.beans.Node;

public class Test {

	public static void main(String[] args) {
		
		ArrayList<Node> nodes = new ArrayList<>();
		
		// TODO Auto-generated method stub
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		Node f = new Node("F");
		
		a.ajouteArc(5, b);
		a.ajouteArc(3, c);
		b.ajouteArc(7, d);
		b.ajouteArc(2, e);
		c.ajouteArc(5, e);
		e.ajouteArc(1, f);
		d.ajouteArc(1, f);
		
		nodes.add(a);
		nodes.add(b);
		nodes.add(c);
		nodes.add(d);
		nodes.add(e);
		nodes.add(f);
		
		for(Node node:nodes)
			for(Arc arc:node.getArcs())
				System.out.println("Arc de " + node.getLibelle() + " à " + arc.getDest().getLibelle() + " avec une valeur de " + arc.getValeur());

		System.out.println("-----------------------------------------");
		// test existChemin
		if(a.existeChemin(f))
			System.out.println("Il existe un chemin entre A et F");
		else
			System.out.println("Pas de chemin entre A et F");
	}

}
