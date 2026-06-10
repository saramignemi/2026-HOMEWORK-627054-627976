package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	
	private Attrezzo attrezzo;
	
	public Mago(String nome, String attrezzo, int peso) {
		super(nome, (	nome + ": Non dovresti soprendere così una persona anziana, rischi che io ti maledica!!\n"+ 
						nome + ": Io sono " + nome + " " + "il Mago, piacere di conoscerti\n"+
						nome + ": Nessun rancore per lo spavento, tanto devo tornare ai miei studi sul peso degli Attrezzi\n"),
						nome + ": Perdonami ma ho veramente molto da fare, gli studi sul peso degli attrezzi mi richiedono molto tempo\n"+
						nome + ": Se solo avessi un attrezzo su cui fare pratica");
		this.attrezzo = new Attrezzo(attrezzo, peso);
	}

	@Override
	public String getRuolo() {
		return "il Mago";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		attrezzo.setPeso(attrezzo.getPeso() / 2);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		return (this.getNome() + ": Ah grazie mille, mi serviva proprio un attrezzo per provare una cosa\n" + 
				this.getNome() + ": Ora vediamo se ho capito bene...\n"+
				this.getNome() + ": Si mi sembra perfetto, te lo lascio qui nella Stanza va bene?\n"+
				"Il Mago ha dimezzato il peso di " + attrezzo.getNome() + " e lo ha lasciato nella Stanza");
	}

	@Override
	public String agisci(Partita partita) {
		if (!this.haSalutato()) {
			return "Il Mago non ti sente, sembra concentrato su qualcosa";
		}
		else {
			String messaggio;
			if (this.attrezzo != null) {
				partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
				messaggio = this.getNome() + ": Tieni questo " + this.attrezzo.getNome() + ", ti servirà sicuramente";
				this.attrezzo = null;
			}
			else {
				 messaggio = this.getNome() + ": Mi dispiace non ho altro";
			}
			return messaggio;
		}
	}
}
