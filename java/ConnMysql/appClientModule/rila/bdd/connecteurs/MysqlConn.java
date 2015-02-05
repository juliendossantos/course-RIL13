package rila.bdd.connecteurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConn
{
	/**
	 * Champ privé connection
	 */
	private Connection connection=null;

	/**
	 * Ce constructeur demande 4 paramètres :
	 * @param ip (String) : IP du serveur <b>mysql</b> ("localhost", par ex.)
	 * @param login (String) : login mysql
	 * @param pass (String) : le pass
	 * @param base (String) : quelle base
	 */
	public MysqlConn(String ip,String login,String pass,String base)
	{
		try
		{
			// Chargement du driver
			String driver = "com.mysql.jdbc.Driver";

			Class.forName( driver );
			// Connexion
			String url = "jdbc:mysql://"+ip+"/"+base;
			connection=DriverManager.getConnection( url, login, pass );
		} catch (ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Constructeur avec paramètres par défaut
	 * @param base (String) : le nom de la base
	 * <ul>
	 * <li>ip par défaut à "localhost"</li>
	 * <li>login par défaut à "root"</li>
	 * <li>password par défaut à root</li></ul>
	 * 
	 * @see #MysqlConn(String, String, String, String)
	 */
	public MysqlConn(String base)
	{
		this("localhost","root","root",base);
	}

	/**
	 * Envoie une requête de type SELECT et retourne une collection de type ResultSet
	 * @param sql (String) : peut être une requête SQL bien formée ou simplement le nom d'une table
	 * @return (ResultSet) @see {@link ResultSet}
	 */
	public ResultSet select(String sql)
	{
		// Si le paramètre ne contient pas d'espace, on crée la requête SQL complète
		if(!sql.contains(" "))
			sql="SELECT * FROM "+sql;
		ResultSet rsResultat=null;

		try
		{
			Statement instruction = connection.createStatement();
			rsResultat = instruction.executeQuery(sql);
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return rsResultat;
	}
	/**
	 * Méthode pour INSERT, DELETE et UPDATE
	 * @param sql (String) : la requête SQL bien formée
	 * @return (int) : le nombre d'enregistrements affectés par la requête
	 */
	public int update(String sql)
	{
		int nb=-1;
		try
		{
			Statement instruction = connection.createStatement();
			nb = instruction.executeUpdate(sql);
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return nb;
	}
	
	/**
	 * Requête SELECT Mais renvoie l'info dans un String[][]
	 * <ul>
	 * <li>Ligne 0 : les étiquettes des colonnes</li>
	 * <li>lignes n : les données
	 * @param sql  (String) : la requête SQL bien formée ou le nom de la table
	 * @return (String[][]) : le tableau
	 */
	public String[][] selectS(String sql)
	{
		String[][] tab=null;
		try
		{
			// on récupère le resultSet de notre requête
			ResultSet rs=select(sql);
			// on récupère le resultSetMetaData
			ResultSetMetaData rsmd=rs.getMetaData();
			// on récupère le nombre de colonnes
			int nbCol=rsmd.getColumnCount();
			// le nombre de lignes
			rs.last();
			int nbLig=rs.getRow();
			rs.beforeFirst();
			// on instancie le tableau
			tab=new String[nbLig+1][nbCol];
			// on remplit la ligne 0 avec les étiquettes
			for(int c=0;c<nbCol;c++)
				tab[0][c]=rsmd.getColumnLabel(c+1);
			// on remplit les autres lignes
			int ligne=1;
			while(rs.next())
			{
				for(int c=0;c<nbCol;c++)
					tab[ligne][c]=rs.getString(c+1);
				ligne++;
			}
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return tab;
	}
	
	/**
	 * Requête d'insertion + retourne le dernier auto-incrément généré
	 * @param sql (String) : Requête d'insertion
	 * @return (int) : je viens de le dire, banane
	 */
	public int insertAI(String sql)
	{
		int nb=-1;
		try
		{
			Statement instruction = connection.createStatement();
			instruction.executeUpdate(sql);
			ResultSet rs=instruction.executeQuery("SELECT LAST_INSERT_ID() AS ai");
			rs.next();
			nb=rs.getInt("ai");
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return nb;
	}
}
