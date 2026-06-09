package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}
	@Override
	public String getNome() {
		return "fine";
	
	}
}
