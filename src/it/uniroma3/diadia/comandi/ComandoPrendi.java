package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Funzione da eseguire dal comando prendi,
 * prende l'attrezzo dalla stanza corrente 
 * e lo mette nella borsa.
 * Se il nome non viene passato allora mostra 
 * tutti gli elementi della stanza.
 * @param nomeAttrezzo Il nome dell'attrezzo da prendere
 */


public class ComandoPrendi implements Comando {
	private IO io;
	private String nomeAttrezzo;

	public ComandoPrendi(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		Attrezzo appoggio; 
		Stanza stanzaCorrente = partita.getStanzaCorrente(); 

		if (this.nomeAttrezzo == null) {
			this.io.mostraMessaggio("Ecco gli attrezzi disponibili\n" + partita.getStanzaCorrente().getDescrizione()); 
			return;
		}

		if (stanzaCorrente.hasAttrezzo(this.nomeAttrezzo)) {
			appoggio = stanzaCorrente.getAttrezzo(this.nomeAttrezzo); 
			boolean nellaBorsa = partita.getGiocatore().getBorsa().addAttrezzo(appoggio); 
			if (nellaBorsa) {
				stanzaCorrente.removeAttrezzo(appoggio); // Ignoro il valore di ritorno dato che già controllo se la stanza ha l'attrezzo
				this.io.mostraMessaggio("Hai raccolto " + this.nomeAttrezzo + "!"); 
			}
			else {
				this.io.mostraMessaggio("La borsa è piena :("); 
			}
		}
		else {
			this.io.mostraMessaggio("Questa stanza non contiene l'attrezzo che hai chiesto"); 
			this.io.mostraMessaggio("Ecco gli attrezzi disponibili\n" + partita.getStanzaCorrente().getDescrizione()); 
		}

	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;

	}
	@Override
	public String getNome() {
		return "prendi";	
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
