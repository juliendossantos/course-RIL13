package rila.bdd.lanceurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import rila.bdd.connecteurs.MysqlConn;

public class intro02 {

	public static void main(String[] args) {
		
		// déclaration et instanciation de l'objet
		MysqlConn mysqlConn = new MysqlConn("films");
		// appel de la méthode select -> ResultSet
		ResultSet resultat = mysqlConn.select("acteurs");
		
		try {
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
