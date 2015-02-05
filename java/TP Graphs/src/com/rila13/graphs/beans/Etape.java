package com.rila13.graphs.beans;

public class Etape {


	private Node nSource;
	private Node nDest;
	private Arc arc;
	
	public Etape(Node nSource, Node nDest, Arc a) {
		this.nSource = nSource;
		this.nDest = nDest;
		arc = a;
	}
	
	public String outPut() {
		return String.format(
					"De %s vers %s :\n"
					+ "\tDistance=%.0f km - "
					+ "Vitesse autorisée : %.0f km/h - "
					+ "Volume Gazole : %.1f l - "
					+ "Temps : %.1f h - "
					+ "Coût total : %.1f €\n", 
					nSource.getLibelle(),
					nDest.getLibelle(),
					arc.getDistance(),
					arc.getVitesse(),
					arc.getVolumeGazole(),
					arc.getTemps(),
					arc.getCout()
				);
	}

	public Arc getArc() {
		return arc;
	}

}
