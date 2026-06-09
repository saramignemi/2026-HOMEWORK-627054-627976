package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	private String nome;
	
	public AbstractPersonaggio(String nome) {
		this.nome = nome;
	}
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);
	

	public String getNome() {
		return this.nome;
	}
	
	public String getRuolo() {
		return "Lo Sconosciuto";
	}
	
	@Override
	public String toString() {
		return this.nome + " " + this.getRuolo();
	}
}
