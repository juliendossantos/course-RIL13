package com.rila13.graphs.beans;

public class Arc {

	private int valeur;
	private Node dest;

	public Arc(int valeur, Node dest)
	{
		this.valeur = valeur;
		this.dest = dest;
	}

	public int getValeur()
	{
		return valeur;
	}

	public Node getDest()
	{
		return dest;
	}

}
