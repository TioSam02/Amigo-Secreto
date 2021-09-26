package br.ufpb.amigosecreto;

import java.util.Objects;

public class Amigo {
	private String nome;
	private String email;
	private String emailAmigoSorteado;
	
	public Amigo() {
		this.nome = "";
		this.email = "";
		this.emailAmigoSorteado = "";
	}
	public Amigo(String nomeAmigo, String emailAmigo) {
		this.nome = nomeAmigo;
		this.email = emailAmigo;
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
	public void setEmail (String email) {
		this.email = email;
	}
	public String getEmailAmigoSorteado() {
		return emailAmigoSorteado;
	}
	public void setAmigoSorteado(String emailAmigoSorteado) {
		this.emailAmigoSorteado = emailAmigoSorteado;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Amigo other = (Amigo) obj;
		return Objects.equals(email, other.email);
	}

}
