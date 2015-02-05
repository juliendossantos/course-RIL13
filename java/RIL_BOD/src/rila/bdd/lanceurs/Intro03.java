package rila.bdd.lanceurs;

import rila.bdd.connecteurs.MysqlConn;

public class Intro03 {

	public static void main(String[] args) {
		
		// déclaration et instanciation de l'objet
		MysqlConn mysqlConn = new MysqlConn("films");
		// appel de la méthode select -> ResultSet
		String[][] tab = mysqlConn.selectS("SELECT codeActeur, prenomActeur, nomActeur, sexeActeur, anneeNaissanceActeur FROM acteurs");
		
		try {
			for (int i = 0; i < tab.length; i++) {
				for (int j = 0; j < tab[i].length; j++) {
					System.out.print(tab[i][j]);
					for (int j2 = 0; j2 < (tab[0][j].length() - tab[i][j].length()); j2++) {
						System.out.print(" ");
					}
					System.out.print("\t");
					
				}	System.out.println();		
			}
		} catch( Exception e ) {
			System.err.println(e.getMessage());
		}
	}

}
