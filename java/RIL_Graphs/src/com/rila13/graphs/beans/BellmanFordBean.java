package com.rila13.graphs.beans;

public class BellmanFordBean {
	
	public static final int INFINI = 999999;
	private int valeur;
	private Node node;
	
	public BellmanFordBean(int valeur,Node node) 
	{
		this.valeur = valeur;
		this.node = node;
	}

	public int getValeur() {
		return valeur;
	}
	
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public Node getNode() {
		return node;
	}
	
	public void setNode(Node node) {
		this.node = node;
	}
	
	public String toString(){
		return String.format("(%s,%s)",
				(valeur==INFINI?"INFINI":String.valueOf(valeur)),
				(node==null?"NULL":node.getLibelle())
		);
	}

}
