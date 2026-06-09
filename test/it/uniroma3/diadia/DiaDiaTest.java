package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class DiaDiaTest {

	private IO io;
	private DiaDia partita;
	private Labirinto labirinto = new LabirintoBuilder()

		    // stanze
		    .addStanzaBloccata("Atrio", "nord")          // StanzaBloccata
		    .addStanzaBuia("Aula N11")                   // StanzaBuia
		    .addStanza("Aula N10")                       // Stanza normale
		    .addStanza("Laboratorio Campus")
		    .addStanza("Biblioteca")

		    // iniziale e vincente
		    .addStanzaIniziale("Atrio")
		    .addStanzaVincente("Biblioteca")

		    // adiacenze (uguali all’originale)
		    .addAdiacenza("Atrio", "Biblioteca", "nord")
		    .addAdiacenza("Atrio", "Aula N11", "est")
		    .addAdiacenza("Atrio", "Aula N10", "sud")
		    .addAdiacenza("Atrio", "Laboratorio Campus", "ovest")

		    .addAdiacenza("Aula N11", "Laboratorio Campus", "est")
		    .addAdiacenza("Aula N11", "Atrio", "ovest")

		    .addAdiacenza("Aula N10", "Atrio", "nord")
		    .addAdiacenza("Aula N10", "Aula N11", "est")
		    .addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")

		    .addAdiacenza("Laboratorio Campus", "Atrio", "est")
		    .addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")

		    .addAdiacenza("Biblioteca", "Atrio", "sud")

		    // attrezzi
		    .inStanza("Aula N10")
		    .addAttrezzo("lanterna", 3)

		    .inStanza("Atrio")
		    .addAttrezzo("osso", 1)

		    .inStanza("Laboratorio Campus")
		    .addAttrezzo("pc", 2)

		    .inStanza("Aula N11")
		    .addAttrezzo("passepartout", 1)

		    // build finale
		    .getLabirinto();
	@Test
	public void testVittoria() throws Exception {
		this.io = new IOSimulator(Arrays.asList(
				"vai sud",
				"prendi lanterna",
				"vai nord",
				"vai est",
				"posa lanterna",
				"prendi passepartout",
				"vai ovest",
				"posa passepartout",
				"vai nord"
				));
				
		this.partita = new DiaDia(this.io, labirinto);
		this.partita.gioca();
		assertEquals("Hai vinto!", ((IOSimulator)this.io).getLastString());
	}
	@Test
	public void testCfuFiniti() throws Exception {
		String[] comandi = new String[20];
		for (int i = 0; i < 20; i++) {
			if (i % 2 == 0) comandi[i] = "vai sud";
			else comandi[i] = "vai nord";
		}
		this.io = new IOSimulator(Arrays.asList(comandi));
				
		this.partita = new DiaDia(this.io, labirinto);
		this.partita.gioca();
		assertEquals("Hai esaurito i CFU...", ((IOSimulator)this.io).getLastString());
	}

}
