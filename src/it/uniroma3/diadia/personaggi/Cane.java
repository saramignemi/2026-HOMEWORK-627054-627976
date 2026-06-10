package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	private Attrezzo attrezzo;
	private String cibo;
	
	public Cane(String nome, Attrezzo a, String cibo) {
		super(nome, "Lui è timoroso di vederti, e sembra voglia qualcosa", "Sembra innervosito dal tuo continuo saluto");
		this.attrezzo = a;
		this.cibo = cibo;
	}
	
	@Override
	public String getRuolo() {
		return "il Cane";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo.getNome().equals(this.cibo)) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			String nome = this.attrezzo.getNome();
			this.attrezzo = null;
			return (this.getNome() + " sembra apprezzare, e per prendere il regalo che gli fai lascia cadere un " + nome);
		}
		else {
			partita.getGiocatore().setCfu(partita.getCfu() - 1);
			return (this.getNome() + " non sembra apprezzare, e ti morde (-1 CFU)");
		}
	}

	@Override
	public String agisci(Partita partita) {
		partita.setCfu(partita.getCfu() - 1);
		return "Il Cane ti morde (-1 CFU)";
	}
	
}
