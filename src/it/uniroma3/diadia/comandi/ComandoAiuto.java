package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	private IO io;
	private String[] elencoComandi;

	public ComandoAiuto(IO io, String[] elencoComandi) {
		this.io = io;
		this.elencoComandi = elencoComandi;
	}
	
	@Override
	public void esegui(Partita partita) {
		String commands = "";
		for(int i=0; i< elencoComandi.length; i++) 
			commands = commands.concat(elencoComandi[i].concat(" "));
		this.io.mostraMessaggio(commands);
	}

	@Override
	public void setParametro(String parametro) {
		// Non prende parametri
	}

	@Override
	public String getNome() {
		return "aiuto";
	
	}

	@Override
	public String getParametro() {
		return null;
	}

}
