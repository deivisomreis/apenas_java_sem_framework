package com.deivison_programmer.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private Connection con;
	private final String serverName = "localhost";
	private final String database = "teste";
	private final String url = "jdbc:mysql://" + serverName + "/" +  database;
	private final String user = "root";
	private final String senha = "1234";
	private final String driver = "com.mysql.jdbc.Driver"; 
	
	public Connection getConnection(){
		try{
			Class.forName(driver);
			return con = DriverManager.getConnection(url, user, senha);
		}
		catch(Exception  e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void stopConnection(){
		if(con != null){
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
