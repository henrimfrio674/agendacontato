package br.com.cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public Connection getConnection() throws Exception {

		// par�metros de conex�o
		String driver = "org.postgresql.Driver";
		String host = "jdbc:postgresql://localhost:5432/bd_agendacontatos";
		String user = "Henrique";
		String password = "Rcgh2012";

		Class.forName(driver);
		return DriverManager.getConnection(host, user, password);
	}

}
