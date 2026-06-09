package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio p = partita.getStanzaCorrente().getPersonaggio();
	    if (p == null) {
	        this.io.mostraMessaggio("Non c'è nessun personaggio qui");
	        return;
	    }
	    if (p.getRuolo().equals("il Mago")) {
		    this.io.mostraMessaggio("Fai una domanda a " + p);
	    	this.io.mostraMessaggio(p.getNome() + ": Non dovresti soprendere così una persona anziana, rischi che io ti maledica!!");
	    	this.io.mostraMessaggio(p.getNome() + ": Nessun rancore comunque, tanto devo tornare ai miei studi sul peso degli Attrezzi");
	    }
	    else if (p.getRuolo().equals("la Strega")) {
	    	this.io.mostraMessaggio("Chiedi a " + p + " cosa ci faccia qui");
	    	this.io.mostraMessaggio(p.getNome() + ": Voglio aiutare gli studenti ovviamente");
	    }
	    else if (p.getRuolo().equals("il Cane"))
	    	this.io.mostraMessaggio("Accarezzi " + p + " e lui appare più contento");
	    else 
	    	this.io.mostraMessaggio("Non dovresti parlare con gli sconosciuti");
	}

	@Override
	public String getNome() {
		return "interagisci";
	}

}
