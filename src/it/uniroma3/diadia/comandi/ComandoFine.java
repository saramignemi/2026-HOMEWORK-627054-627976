package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	private IO io;

	public ComandoFine(IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}

	@Override
	public void setParametro(String parametro) {
		// Non prende parametri

	}
	@Override
	public String getNome() {
		return "fine";
	
	}

	@Override
	public String getParametro() {
		return null;
	}

}
