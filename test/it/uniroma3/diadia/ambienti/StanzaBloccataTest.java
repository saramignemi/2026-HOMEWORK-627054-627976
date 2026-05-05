package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	private StanzaBloccata stanza;
	
	@Before
	public void setUp() {
		this.stanza = new StanzaBloccata("stanza", "test", "nord");
	}

	@Test
	public void testVaiBloccato() {
		assertSame(this.stanza, this.stanza.getStanzaAdiacente("nord"));
	}

	@Test
	public void testVaiSbloccato() {
		this.stanza.addAttrezzo(new Attrezzo("test", 1));
		assertEquals(null, this.stanza.getStanzaAdiacente("nord"));
	}

	@Test
	public void testVaiBloccatoConAttrezzo() {
		this.stanza.addAttrezzo(new Attrezzo("sbagliato", 1));
		assertSame(this.stanza, this.stanza.getStanzaAdiacente("nord"));
	}

}
