package rila.bdd.lanceurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Intro01 {

	public static void main(String[] args) {
		try {
			// Chargement du driver
			String driver = "com.mysql.jdbc.Driver";
			Class.forName( driver );
			// Connexion 
			String url = "jdbc:mysql://localhost/films";
			Connection connection=DriverManager.getConnection( url, "root", "root" );
			// Requête
			Statement instruction = connection.createStatement();
			ResultSet resultat = instruction.executeQuery("SELECT * FROM acteurs");
			// Affichage
			while(resultat.next()){
				System.out.println("­­­­­­­­­­­­­­­­­­­­­­­­­­­");
				System.out.println("Code : "+resultat.getString("codeActeur"));
				System.out.println("Prénom : "+resultat.getString("prenomActeur"));
				System.out.println("Nom : "+resultat.getString("nomActeur"));
				System.out.println("Sexe : "+resultat.getInt("sexeActeur"));
				System.out.println("Année de naissance : "+resultat.getInt("anneeNaissanceActeur"));
			}
		} catch( Exception e ) {
			System.err.println(e.getMessage());
		}
	}

}
