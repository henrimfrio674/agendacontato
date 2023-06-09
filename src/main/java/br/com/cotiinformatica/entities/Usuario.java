package br.com.cotiinformatica.entities;

import java.util.List;

public class Usuario {
	private Integer idContato;
	private String nome;
	private String email;
	private String senha;
	private List<Contato> contatos;
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	public Usuario(Integer idContato, String nome, String email, String senha, List<Contato> contatos) {
		super();
		this.idContato = idContato;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.contatos = contatos;
	}
	public Integer getIdContato() {
		return idContato;
	}
	public void setIdContato(Integer idContato) {
		this.idContato = idContato;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Contato> getContatos() {
		return contatos;
	}
	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
	
}
