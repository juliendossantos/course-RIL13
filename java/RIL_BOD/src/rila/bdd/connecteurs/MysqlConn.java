package rila.bdd.connecteurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class MysqlConn {

	private Connection connection = null;

	/**
	 * Ce constructeur demande 4 paramètres :
	 * @param ip (String) : IP du serveur <b>Mysql</b> ("localhost", par ex.)
	 * @param login (String) : login Mysql
	 * @param password (String) : password Mysql
	 * @param base (String) : Nom de la base de donnée
	 */

	public MysqlConn(String ip, String login, String password, String base) {
		try {
			// Chargement du driver
			String driver = "com.mysql.jdbc.Driver";
			Class.forName( driver );
			// Connexion 
			String url = "jdbc:mysql://" + ip + "/" + base;
			connection = DriverManager.getConnection( url, login, password );
		} catch (ClassNotFoundException e){
			System.err.println(e.getMessage());
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Constructeur avec paramètre par défaut
	 * @param base (String) : Nom de la base de donnée
	 * <ul>
	 * <li>ip par defaut à "localhost"</li>
	 * <li>login par defaut "root"</li>
	 * <li>mot de passe par défaut "root"</li>
	 * </ul>
	 * @see MysqlConn(String, String, String, String)
	 */
	public MysqlConn(String base) {
		this("localhost", "root", "root", base);
	}

	/**
	 * Méthode pour SELECT dans la base de donnée
	 * @param requete (String) : la requéte sql ou le nom de la table pour un show all
	 * @return
	 */
	public ResultSet select(String sql) {
		// Si le paramètre ne contient pas d'espace, on crée la requéte SQL complete
		if(!sql.contains(" "))
			sql = "SELECT * FROM " + sql;
		ResultSet rsResultat = null;
		
		try {
			Statement instruction = connection.createStatement();
			rsResultat = instruction.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rsResultat;
	}
	
	/**
	 * Méthode pour INSERT, DELETE and UPDATE
	 * @param sql String : la requète sql bien formaté
	 * @return (int) : le nombre d'enregistrement affectés par la requéte
	 */
	public int update(String sql) {
		int nb = 1;
		try {
			Statement instruction = connection.createStatement();
			nb = instruction.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return nb;
	}
	
	public String[][] selectS(String sql) {
		String[][] tab = null;
		int nbLigne;
		int nbColumn;

		try {
			
			ResultSet rs = select(sql);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			nbColumn = rsMetaData.getColumnCount();
			nbLigne = getNbLignes(rs);
			tab = new String[nbLigne+1][nbColumn];

			for (int c = 0; c < nbColumn; c++) {
				tab[0][c] = rsMetaData.getColumnLabel(c+1);
			}

			int l = 1;
			while(rs.next()) {
				for (int c = 0; c < nbColumn; c++) {
					tab[l][c] = rs.getString(c+1);
				}
				l++;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return tab;

	}
	
	public int getNbLignes(ResultSet result) {
		int nb = 0;

		try {
			result.last();
			nb = result.getRow();
			result.beforeFirst();			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return nb;
	}
}
