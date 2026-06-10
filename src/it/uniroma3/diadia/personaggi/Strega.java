package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	
	public Strega(String nome) {
		super(nome, "Lei ricambia il saluto", (nome + ": Ma non ti ho gia salutato?"));
	}

	@Override
	public String getRuolo() {
		return "la Strega";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "La strega prende il regalo e scoppia a ridere";
	}

	@Override
	public String agisci(Partita partita) {
		String messaggio = this.getNome() + ": Secondo me ti troverai meglio in questa stanza!!";
		partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacente((Direzione) partita.getStanzaCorrente().getDirezioni().toArray()[0]));
		return messaggio;
	}
}
