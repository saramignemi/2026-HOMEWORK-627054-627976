package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	public Cane(String nome) {
		super(nome);
	}
	
	@Override
	public String getRuolo() {
		return "il Cane";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		//TODO da implementare
		return null;
	}
}
