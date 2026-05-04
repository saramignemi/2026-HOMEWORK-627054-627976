package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private IO io;

	public ComandoNonValido(IO io) {
		this.io = io;
	}
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comando non valido, usa il comando aiuto per sapere quali sono i comandi validi");

	}

	@Override
	public void setParametro(String parametro) {
		// non prende parametri

	}
	@Override
	public String getNome() {
		return "INVALID";

	}

	@Override
	public String getParametro() {
		return null;
	}

}
