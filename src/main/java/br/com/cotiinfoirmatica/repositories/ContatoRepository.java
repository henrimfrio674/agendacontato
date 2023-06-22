package br.com.cotiinfoirmatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.entities.Contato;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class ContatoRepository {
    
    // método para inserir um contato no banco de dados
    public void create(Contato contato) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.getConnection();
        
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO contato (nome, telefone, email, observacoes, idusuario) VALUES (?, ?, ?, ?, ?)");

        statement.setString(1, contato.getNome());
        statement.setString(2, contato.getTelefone());
        statement.setString(3, contato.getEmail());
        statement.setString(4, contato.getObservacoes());
        statement.setInt(5, contato.getIdUsuario());
        statement.execute();
        
        connection.close();
    }

    // método para atualizar um contato no banco de dados
    public void update(Contato contato) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.getConnection();
        
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE contato SET nome=?, telefone=?, email=?, observacoes=? WHERE idcontato=?");

        statement.setString(1, contato.getNome());
        statement.setString(2, contato.getTelefone());
        statement.setString(3, contato.getEmail());
        statement.setString(4, contato.getObservacoes());
        statement.setInt(5, contato.getIdContato());
        statement.execute();
        
        connection.close();
    }

    // método para excluir um contato no banco de dados
    public void delete(Contato contato) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.getConnection();
        
        PreparedStatement statement = connection.prepareStatement("DELETE FROM contato WHERE idcontato=?");

        statement.setInt(1, contato.getIdContato());
        statement.execute();
        
        connection.close();
    }

    // método para consultar todos os contatos de um determinado usuário no banco de dados
    public List<Contato> findByUsuario(Integer idUsuario) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.getConnection();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM contato WHERE idusuario=?");

        statement.setInt(1, idUsuario);
        ResultSet resultSet = statement.executeQuery();
        
        List<Contato> lista = new ArrayList<Contato>();
        
        while (resultSet.next()) {
            Contato contato = new Contato();
            contato.setIdContato(resultSet.getInt("idcontato"));
            contato.setNome(resultSet.getString("nome"));
            contato.setEmail(resultSet.getString("email"));
            contato.setTelefone(resultSet.getString("telefone"));
            contato.setObservacoes(resultSet.getString("observacoes"));
            contato.setIdUsuario(resultSet.getInt("idusuario"));
            lista.add(contato);
        }
        
        connection.close();
        return lista;
    }

    // método para consultar 1 contato no banco de dados através do ID (primary key)
    public Contato findById(Integer idContato) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.getConnection();
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM contato WHERE idcontato=?");

        statement.setInt(1, idContato);
        ResultSet resultSet = statement.executeQuery();
        Contato contato = null;
        
        if (resultSet.next()) {
            contato = new Contato();
            contato.setIdContato(resultSet.getInt("idcontato"));
            contato.setNome(resultSet.getString("nome"));
            contato.setEmail(resultSet.getString("email"));
            contato.setTelefone(resultSet.getString("telefone"));
            contato.setObservacoes(resultSet.getString("observacoes"));
            contato.setIdUsuario(resultSet.getInt("idusuario"));
        }
        
        connection.close();
        return contato;
    }
}