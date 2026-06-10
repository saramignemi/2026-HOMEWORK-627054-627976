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
	    io.mostraMessaggio(p.saluta());
	}

	@Override
	public String getNome() {
		return "saluta";
	}

}
