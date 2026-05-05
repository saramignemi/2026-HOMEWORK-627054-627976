package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;


/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai implements Comando {
	private String direzione;
	private IO io;

	public ComandoVai(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		if(this.direzione==null)
			this.io.mostraMessaggio("Dove vuoi andare ?");
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				this.io.mostraMessaggio("Direzione inesistente");
			else {
				if (prossimaStanza == partita.getStanzaCorrente())
					this.io.mostraMessaggio("Questa direzione è bloccata");
				partita.setStanzaCorrente(prossimaStanza);
				int cfu = partita.getCfu();
				partita.setCfu(--cfu);	
			}
		}

		if(!partita.getStanzaCorrente().getNome().equals("Biblioteca")) {
			this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		}
		else {
			this.io.mostraMessaggio("Complimenti, sei arrivato in " + partita.getStanzaVincente().getNome() + "."); 
		}

	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;

	}
	@Override
	public String getNome() {
		return "vai";	
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}

}
