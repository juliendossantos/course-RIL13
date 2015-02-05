package com.rila13.graphs.beans;

public class BellmanFordBean {
	
	public static final float INFINI = Float.POSITIVE_INFINITY;
	private float valeur;
	private Node node;
	
	public BellmanFordBean(float valeur,Node node) {
		this.valeur = valeur;
		this.node = node;
	}

	public float getValeur() {
		return valeur;
	}
	
	public void setValeur(float valeur) {
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
