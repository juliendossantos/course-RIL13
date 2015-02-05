package com.rila13.graphs.beans;

public class Arc {

	private float distance;
	private float vitesse;
	private float peage;
	private Node dest;

	public Arc(float distance, float vitesse, float peage, Node dest)
	{
		this.distance = distance;
		this.vitesse = vitesse;
		this.peage = peage;
		this.dest = dest;
	}

	public float getDistance() {
		return distance;
	}

	public float getVitesse() {
		return vitesse;
	}


	public float getPeage() {
		return peage;
	}

	public Node getDest() {
		return dest;
	}
	
	public float getVolumeGazole() {
		return (Parameters.a*vitesse+Parameters.b)*distance/100;
	}
	
	public float getCoutGazole() {
		return Parameters.coutLitreGazole*getVolumeGazole();
	}
	
	public float getTemps() {
		return distance/vitesse;
	}
	
	public float getCout() {
		return getCoutGazole()+peage;
	}

}
