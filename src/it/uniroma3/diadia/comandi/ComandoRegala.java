package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		Giocatore giocatore = partita.getGiocatore();
		Stanza stanza = partita.getStanzaCorrente();
		AbstractPersonaggio p = stanza.getPersonaggio();
		
		if (p == null) this.io.mostraMessaggio("Non c'è nessuno in questa stanza...");
		else {
			if (this.nomeAttrezzo == null) {
				io.mostraMessaggio("Devi dare anche il nome di un attrezzo presente nella borsa");
			}
			else if (!giocatore.getBorsa().hasAttrezzo(this.nomeAttrezzo)) {
				io.mostraMessaggio("Non hai alcun attrezzo chiamato " + this.nomeAttrezzo);
			}
			else {
				Attrezzo regalo = giocatore.getBorsa().removeAttrezzo(this.nomeAttrezzo);
				io.mostraMessaggio(p.riceviRegalo(regalo, partita));
			}
		}

	}
	
	@Override
	public void setParametro(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
	
	@Override
	public String getNome() {
		return "regala";
	}

}
