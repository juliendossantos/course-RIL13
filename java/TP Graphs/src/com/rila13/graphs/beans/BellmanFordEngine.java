package com.rila13.graphs.beans;

public class BellmanFordEngine {
	
	private BellmanFordBean[] binomes;
	private Node[] nodes;
	private Trajet trajet;
	
	public BellmanFordEngine (Node[] nodes) {
		this.nodes = nodes;
		binomes = new BellmanFordBean[nodes.length];
		trajet = new Trajet();
	}
	
	public void init() {
		trajet.getEtapes().clear();
		binomes[0]= new BellmanFordBean(0,nodes[0]);
		for(int i=1;i<nodes.length; i++)
		{
			binomes[i] = new BellmanFordBean(BellmanFordBean.INFINI, null);
		}
	}
	
	public void startEngine(TypeOptim type) {
		
		while(true) {
			boolean change = false;
			for(int iSource = 0; iSource < nodes.length; iSource++)
			{
				for(int iDest = 0;iDest <nodes.length;iDest++)
				{
					Arc a = nodes[iSource].existeCheminDirect(nodes[iDest]);
					if(a != null)
					{
					
						float valeur = 0;
						
						switch(type.toString()){
							case "distance":
								valeur = binomes[iSource].getValeur()+a.getDistance();
								break;
							case "temps":
								valeur = binomes[iSource].getValeur()+a.getTemps();
								break;
							case "cout":
								valeur = binomes[iSource].getValeur()+a.getCout();
								break;
							case "gazole":
								valeur = binomes[iSource].getValeur()+a.getCoutGazole();
								break;
						}
						if(binomes[iDest].getValeur() > valeur) {
							binomes[iDest].setValeur(valeur);
							binomes[iDest].setNode(nodes[iSource]);
							change = true;
						}
						
					}
				}
			}
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
	
	public String[] outPut() {
		String[] out = new String[binomes.length];
		Node nSource = null;
		Node nDest = null;
		Arc a = null;

		for(int i = binomes.length-1; i >= 0; i--)
		{	
			nSource = binomes[i].getNode();
			
			if(nDest == null)
				nDest = nodes[i];

			a = nSource.existeCheminDirect(nDest);
			if(a != null)
			{
				out[i] = nSource.getLibelle() + " " + nDest.getLibelle() + " : (vit. : " + a.getVitesse()+"), (dist. : " + a.getDistance() + "), (temps : " + a.getTemps() + "), (coût : " + a.getCout() + ")";
				nDest = nSource;
			}
		}
		return out;
	}
	
	public void initTrajet() {
		Node nSource = null;
		Node nDest = null;
		Arc a = null;

		for(int i = binomes.length-1; i >= 0; i--)
		{	
			nSource = binomes[i].getNode();
			
			if(nDest == null)
				nDest = nodes[i];

			a = nSource.existeCheminDirect(nDest);
			if(a != null)
			{
				trajet.ajoutEtape(new Etape(nSource, nDest, a));
				nDest = nSource;
			}
		}

	}

	public BellmanFordBean[] getBinomes() {
		return binomes;
	}

	public Trajet getTrajet() {
		return trajet;
	}

}
