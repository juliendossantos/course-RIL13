package com.ingesup.java.mysql.connectors;

import java.sql.*;
import java.util.ArrayList;

public class ConnMysql 
{
	private Connection con;
	/**
	 * Constructeur de la classe
	 * @param base (String) : demande le nom de la base
	 */
	public ConnMysql(String base)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			final String url = "jdbc:mysql://localhost/"+base;
			final String login = "root";
			final String pass="root";
			con=DriverManager.getConnection(url,login,pass);
		}
		catch(Exception e)
		{
			System.err.println("Constructeur du connecteur : "+e.getMessage());
		}
	}
	/**
	 * Constructeur de la classe
	 * @param ip (String) : adresse IP du serveur (localhost, par ex.)
	 * @param base (String) : nom de la base
	 * @param login (String) : login de connexion
	 * @param pass (String) : mot de passe
	 */
	public ConnMysql(String ip,String base,String login,String pass)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			final String url = "jdbc:mysql://"+ip+"/"+base;
			con=DriverManager.getConnection(url,login,pass);
		}
		catch(Exception e)
		{
			System.err.println("Constructeur du connecteur : "+e.getMessage());
		}
	}
	/**
	 * Génère une requête select et renvoie un ResultSet
	 * @param sql (String) : peut être une table, ou bien une requête SELECT
	 * @return (ResultSet) : les données de retour
	 */
	public ResultSet select(String sql)
	{
		ResultSet rs=null;
		if(!sql.contains(" "))
			sql="SELECT * FROM "+sql;
		// System.out.println("Requête à lancer : "+sql);
		try
		{
			Statement instruction=con.createStatement();
			rs=instruction.executeQuery(sql);
		}
		catch(Exception e)
		{
			System.err.println("Select : "+e.getMessage());
		}
		return rs;
	}
	/**
	 * Retourne le nom des champs dans un tableau 
	 * @param rs (ResultSet) : les données revenant d'un select
	 * @return (String[]) : le tableau
	 */
	public String[] nomChamps(ResultSet rs)
	{
		String[] champs=null;
		try
		{
			ResultSetMetaData meta=rs.getMetaData();
			final int columnCount = meta.getColumnCount();
			champs=new String[columnCount];
			for(int i=0;i<columnCount;i++)
			{
				champs[i]=meta.getColumnLabel(i+1);
			}
		}
		catch(Exception e)
		{
			System.err.println("Select : "+e.getMessage());
		}
		return champs;
	}
	/**
	 * Getter de la connexion
	 * @return (Connection)
	 */
	public Connection getCon() {
		return con;
	}
	/**
	 * Récupère les noms des tables de la base et les stocke dans un arrayList
	 * @return (Arraylist) : ce que je viens de dire, banane
	 */
	public ArrayList<String> nomTables()
	{
		ArrayList<String> res=new ArrayList<String>();
		try
		{
			DatabaseMetaData dbmd=con.getMetaData();
			ResultSet rsCatalogs = dbmd.getTables(null, null, null, null);
			while( rsCatalogs.next() )
			{
				//System.out.println( rsCatalogs.getString( "TABLE_NAME" ) );
				res.add(rsCatalogs.getString( "TABLE_NAME" ));
			}
			rsCatalogs.close();
		}
		catch(Exception e)
		{
			System.err.println("Select : "+e.getMessage());
		}
		return res;
	}
	/**
	 * Génère une requête SELECT et renvoie un tableau à deux dimensions 
	 * <img src="./doc-files/table.png" />
	 * <ul>
	 * <li>La ligne 0 contiendra le nom des champs</li>
	 * <li>Les lignes 1 à n contiennent les enregistrements</li></ul>
	 * @param sql (String) : la requête SELECT ou la table
	 * @return (String[][]) : le tableau à deux dimensions
	 */
	public String[][] selectTable(String sql)
	{
		String[][] res=null;
		try
		{
			ResultSet rs=select(sql);
			ResultSetMetaData meta=rs.getMetaData();
			// nombre de colonnes
			int nombreCol=meta.getColumnCount();
			// nombre de lignes : on se met au dernier, puis on regarde le numéro de l'enregistrement
			// ne pas oublier de revenir
			rs.last();
			int nombreLig=rs.getRow();
			rs.beforeFirst();
			// on peut enfin créer le tableau
			// on met une ligne de plus pour insérer les étiquettes
			res=new String[nombreLig+1][nombreCol];
			for(int j=0;j<nombreCol;j++)
				res[0][j]=meta.getColumnLabel(j+1);
			int i=1;
			while(rs.next())
			{
				for(int j=0;j<nombreCol;j++)
				{
					res[i][j]=rs.getString(j+1);
					// System.out.println("Je stocke "+res[i][j]+" en "+i+", "+j);
				}
				i++;
			}
		}
		catch(Exception e)
		{
			System.err.println("SelectTable : "+e.getMessage());
		}
		return res;
	}
	/**
	 * A utiliser pour une requête INSERT, UPDATE, ou DELETE
	 * @param sql (String) : requête SQL
	 * @return (int) : le nombre d'enregistrements affectés
	 */
	public int execute(String sql)
	{
		int res=1;
		// System.out.println("Requête à lancer : "+sql);
		try
		{
			Statement instruction=con.createStatement();
			res=instruction.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.err.println("Update : "+e.getMessage());
		}
		return res;
	}
}
