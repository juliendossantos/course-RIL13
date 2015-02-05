package com.rila13.graphs.beans;
import java.util.ArrayList;

public class BellmanFordEngine {
	
	private BellmanFordBean[] binomes;
	private Node[] nodes;
	
	public BellmanFordEngine (Node[] nodes)
	{
		this.nodes = nodes;
		binomes = new BellmanFordBean[nodes.length];
	}
	
	public void init() {
		System.out.print("Init\t");

		binomes[0]= new BellmanFordBean(0,nodes[0]);
		for(int i=1;i<nodes.length; i++)
		{
			binomes[i] = new BellmanFordBean(BellmanFordBean.INFINI, null);
		}
		System.out.println(this);
	}
	
	public void startEngine() {
		
		while(true) {
			System.out.print("Boucle\t");			
			boolean change = false;
			for(int iSource = 0; iSource < nodes.length; iSource++)
			{
				for(int iDest = 0;iDest <nodes.length;iDest++)
				{
					int p = nodes[iSource].existeCheminDirect(nodes[iDest]);
					if(p != -1)
					{
						if(binomes[iDest].getValeur() > binomes[iSource].getValeur()+p) {
							binomes[iDest].setValeur(binomes[iSource].getValeur()+p);
							binomes[iDest].setNode(nodes[iSource]);
							change = true;
						}
					}
				}
			}
			System.out.println(this);
			if(!change)
				break;
		}
	}
	
	public String toString() {
		String res="";
		for(BellmanFordBean bf:binomes)
			res+=String.format("%20s", bf);
		return res;
	}
}
