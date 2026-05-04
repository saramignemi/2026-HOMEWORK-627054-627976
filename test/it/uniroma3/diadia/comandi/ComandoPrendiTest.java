package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {
	private ComandoPrendi prendi;
	private Partita partita;
	
	@Before
	public void setUp() {
		this.prendi = new ComandoPrendi(new IOConsole());
		this.partita = new Partita();
	}

	@Test
	public void testStanzaVuota() {
		this.prendi.setParametro("test");
		this.prendi.esegui(this.partita);
	}
	@Test
	public void testBorsaPienaNonAggiunge() {
		this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("test", 1));
		for (int i = 0; i < 10; i++) {
			this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo(Integer.toString(i), 1));
		}
		this.prendi.setParametro("test");
		this.prendi.esegui(this.partita);
		assertEquals(10, this.partita.getGiocatore().getBorsa().getPeso());
	}
	@Test
	public void testNomeNull() {
		this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("test", 1));
		this.prendi.setParametro(null);
		assertEquals(0, this.partita.getGiocatore().getBorsa().getPeso());
	}

}
