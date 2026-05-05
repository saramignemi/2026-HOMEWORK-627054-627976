package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	private StanzaBuia stanza;
	
	@Before
	public void setUp() {
		this.stanza = new StanzaBuia("stanza", "test");
	}

	@Test
	public void testOggettoNonPresente() {
		assertEquals("qui c'è un buio pesto", this.stanza.getDescrizione());
	}

	@Test
	public void testOggettoPresente() {
		this.stanza.addAttrezzo(new Attrezzo("test", 1));
		assertNotEquals("qui c'è un buio pesto", this.stanza.getDescrizione());
	}
	
	@Test
	public void testOggettoPresenteSbagliato() {
		this.stanza.addAttrezzo(new Attrezzo("sbagliato", 1));
		assertEquals("qui c'è un buio pesto", this.stanza.getDescrizione());

	}


}
