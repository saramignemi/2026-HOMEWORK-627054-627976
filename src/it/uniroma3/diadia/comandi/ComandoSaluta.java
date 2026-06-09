package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.*;

public class ComandoSaluta extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
	    AbstractPersonaggio p = partita.getStanzaCorrente().getPersonaggio();
	    if (p == null) {
	        this.io.mostraMessaggio("Non c'è nessun personaggio qui");
	        return;
	    }
	    this.io.mostraMessaggio("Saluti " + p + "!");
	    if (p.getRuolo().equals("il Mago"))
	    	this.io.mostraMessaggio("Lui sembra pensare ad altro e non ricambia il saluto");
	    else if (p.getRuolo().equals("la Strega"))
	    	this.io.mostraMessaggio("Lei ti invita ad avvicinarti");
	    else if (p.getRuolo().equals("il Cane"))
	    	this.io.mostraMessaggio("Lui è contento di vederti, e sembra voglia qualcosa");
	    else 
	    	this.io.mostraMessaggio("Non dovresti parlare con gli sconosciuti");
	}

	@Override
	public String getNome() {
		return "saluta";
	}

}
