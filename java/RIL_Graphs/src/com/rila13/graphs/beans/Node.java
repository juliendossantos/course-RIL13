package com.rila13.graphs.beans;
import java.util.ArrayList;

public class Node {

	private String libelle;
	private ArrayList<Arc> arcs;

	public Node(String libelle)
	{
		this.libelle = libelle;
		arcs = new ArrayList<>();
		// TODO : other initializations
	}

	public boolean ajouteArc(int value, Node dest)
	{
		// TODO
		Arc a = new Arc(value, dest);
		return arcs.add(a);
	}

	public int existeCheminDirect(Node dest)
	{
		// TODO Aussi
		for(Arc a:arcs)
			if(a.getDest()==dest)
				return a.getValeur();
		return -1;
	}
	
	public boolean existeChemin(Node dest)
	{
		for(Arc a:arcs) 
			if(a.getDest()==dest)
				return true;
			else	
				if(a.getDest().existeChemin(dest))
					return true;

		return false;
	}

	public String getLibelle()
	{
		return libelle;
	}

	public ArrayList<Arc> getArcs()
	{
		return arcs;
	}
}
