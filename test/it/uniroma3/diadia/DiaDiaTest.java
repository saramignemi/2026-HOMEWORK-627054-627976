package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiaDiaTest {

	private IO io;
	private DiaDia partita;
	
	@Test
	public void testVittoria() {
		this.io = new IOSimulator(
				"vai sud",
				"prendi lanterna",
				"vai nord",
				"vai est",
				"posa lanterna",
				"prendi passepartout",
				"vai ovest",
				"posa passepartout",
				"vai nord"
				);
				
		this.partita = new DiaDia(this.io);
		this.partita.gioca();
		assertEquals("Hai vinto!", ((IOSimulator)this.io).getLastString());
	}
	@Test
	public void testCfuFiniti() {
		String[] comandi = new String[20];
		for (int i = 0; i < 20; i++) {
			if (i % 2 == 0) comandi[i] = "vai sud";
			else comandi[i] = "vai nord";
		}
		this.io = new IOSimulator(comandi);
				
		this.partita = new DiaDia(this.io);
		this.partita.gioca();
		assertEquals("Hai esaurito i CFU...", ((IOSimulator)this.io).getLastString());
	}

}
