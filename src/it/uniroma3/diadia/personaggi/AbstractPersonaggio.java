package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private String secondoSaluto;
	private boolean haSalutato;
	
	public AbstractPersonaggio(String nome, String presentazione, String secondoSaluto) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
		this.secondoSaluto = secondoSaluto;
	}
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);
	

	public String getNome() {
		return this.nome;
	}
	
	public String getRuolo() {
		return "Lo Sconosciuto";
	}
	
	public boolean haSalutato() {
		return this.haSalutato;
	}
	
	public String saluta() {
		StringBuilder risposta = new StringBuilder();
		if (!this.haSalutato) {
			risposta.append(this.presentazione);
		}
		else {
			risposta.append(this.secondoSaluto);
		}
		this.haSalutato = true;
		return risposta.toString();
	}
	
	abstract public String agisci(Partita partita);
	
	@Override
	public String toString() {
		return this.nome + " " + this.getRuolo();
	}
}
