package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


/**
 * Funzione da eseguire dal comando posa,
 * prende l'attrezzo dalla borsa 
 * e lo mette nella stanza corrente.
 * Se il nome non viene passato allora mostra 
 * tutti gli attrezzi della borsa.
 * @param nomeAttrezzo Il nome dell'attrezzo da posare
 */

public class ComandoPosa implements Comando {
	private IO io;
	private String nomeAttrezzo;

	public ComandoPosa(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		Attrezzo appoggio; 
		Stanza stanzaCorrente = partita.getStanzaCorrente(); 
		Borsa borsa = partita.getGiocatore().getBorsa(); 

		if (this.nomeAttrezzo == null) {
			this.io.mostraMessaggio("Ecco gli attrezzi nella borsa\n" + partita.getGiocatore().getBorsa().toString()); 
			return;
		}

		if (borsa.hasAttrezzo(this.nomeAttrezzo)) {
			appoggio = borsa.removeAttrezzo(this.nomeAttrezzo); 
			boolean nellaStanza = stanzaCorrente.addAttrezzo(appoggio); 
			if (nellaStanza) {
				this.io.mostraMessaggio("Hai posato " + this.nomeAttrezzo + "!"); 
			}
			else {
				borsa.addAttrezzo(appoggio); 
				this.io.mostraMessaggio("La stanza è piena :("); 
			}
		}
		else {
			this.io.mostraMessaggio("La tua borsa non contiene l'attrezzo che hai chiesto"); 
			this.io.mostraMessaggio("Ecco gli attrezzi nella borsa\n" + partita.getGiocatore().getBorsa().toString()); 
		}

	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;

	}
	@Override
	public String getNome() {
		return "posa";	
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;	
	}

}
