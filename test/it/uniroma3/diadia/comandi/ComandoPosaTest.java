package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	private ComandoPosa posa;
	private Partita partita;
	
	@Before
	public void setUp() {
		this.posa = new ComandoPosa(new IOConsole());
		this.partita = new Partita();
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test", 1));
	}

	@Test
	public void testSvuota() {
		this.posa.setParametro("test");
		this.posa.esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
	}
	@Test
	public void testNomeNull() {
		this.posa.setParametro(null);
		this.posa.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().isEmpty());
	}
	@Test
	public void testAttrezzoNonInBorsa() {
		this.posa.setParametro("ciao");
		this.posa.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().isEmpty());
	}



}
