package com.ingesup.java.mysql.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws SQLException {
		ConnMysql connMysql=new ConnMysql("films");
		ResultSet resS=connMysql.select("acteurs");
		while(resS.next())
		{
			System.out.println(resS.getString("prenomActeur"));
		}
	}

}
