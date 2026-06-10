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
	    io.mostraMessaggio(p.agisci(partita));
	}

	@Override
	public String getNome() {
		return "interagisci";
	}

}
