package br.com.cotiinfoirmatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.cotiinformatica.factories.ConnectionFactory;

public class ContatoRepository {
	public void create() throws Exception{
		
		//abrindo banco
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		Connection connection = connectionFactory.getConnection();
		

	}
}
