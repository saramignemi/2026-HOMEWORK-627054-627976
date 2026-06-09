package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoGuarda extends AbstractComando {	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio p = partita.getStanzaCorrente().getPersonaggio();
		this.io.mostraMessaggio("Posizione attuale:");
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.io.mostraMessaggio("\nContenuto della borsa:");
		this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		this.io.mostraMessaggio("\nCFU rimasti: " + partita.getCfu());
		if (p != null) 
			this.io.mostraMessaggio("\nPersonaggi: " + p);
		else
			this.io.mostraMessaggio("Non c'è nessuno qui");

	}
	@Override
	public String getNome() {
		return "guarda";
	}

}
