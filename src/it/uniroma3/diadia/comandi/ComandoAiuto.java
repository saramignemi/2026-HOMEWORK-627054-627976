package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
	    StringBuilder commands = new StringBuilder();
	    for (String nome : AbstractComando.getNomiComandi())
	        commands.append(nome + " ");
	    this.io.mostraMessaggio(commands.toString());
	}
	
	@Override
	public String getNome() {
		return "aiuto";
	
	}
}
