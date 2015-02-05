package com.rila13.graphs.beans;
import java.util.ArrayList;

public class Trajet {
	
	private ArrayList<Etape> etapes;
	
	public Trajet() {
		etapes = new ArrayList<>();
	}
	
	public void ajoutEtape(Etape e) {
		etapes.add(0,e);
	}

	public ArrayList<Etape> getEtapes() {
		return etapes;
	}
	
	public String outPut()
	{
		String out = "";
		for(int i = 0; i < etapes.size(); i++)
		{
			out += etapes.get(i).outPut();
			out += calculeCumule(i+1);
		}
		return out;
	}
	
	public String calculeCumule(int index){
		float distance = 0;
		float vGazole = 0;
		float temps = 0;
		float cout = 0;

		for(int i = 0; i < index; i++){
			Arc a = etapes.get(i).getArc();
			distance += a.getDistance();
			vGazole += a.getVolumeGazole();
			temps += a.getTemps();
			cout += a.getCout();
		}
		return String.format(
					"\tCumulé : \tDistance=%.0f km - "
					+ "Volume Gazole : %.1f l - "
					+ "Temps : %.1f h - "
					+ "Coût total : %.1f €\n", 
					distance,vGazole,temps,cout
				);
	}
	
}
