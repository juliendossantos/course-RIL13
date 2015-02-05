package exercice1;

import java.util.Scanner;

public class Test {

	private int nombreMemorise,nombrePropose;

	public Test() {
		nombreMemorise=2;
		nombrePropose=2;
		Scanner in=new Scanner(System.in);
		// Déclaration et lancement du thread espion
		ThreadEspion espion = new ThreadEspion(this);
		new Thread(espion).start(); // identique à : new Thread(new ThreadEspion(this)).start();

		while(true){
			afficheMemorise();
			System.out.print("Entrez une nouvelle valeur : ");
			nombrePropose=in.nextInt();
		}

	}

	public void afficheMemorise() {
		System.out.println("Le nombre actuellement mémorisé est "+nombreMemorise);
	}

	public static void main(String[] args) {
		new Test();
	}

	public int getNombreMemorise() { return nombreMemorise; }

	public void setNombreMemorise(int nombreMemorise) { this.nombreMemorise = nombreMemorise; }

	public int getNombrePropose() { return nombrePropose; }

}


