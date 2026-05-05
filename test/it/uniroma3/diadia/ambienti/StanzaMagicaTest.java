package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	private StanzaMagica stanza;
	@Before
	public void setUp() {
		this.stanza = new StanzaMagica("test", 1);
	}

	@Test
	public void testAggiuntaAttrezzo() {
		this.stanza.addAttrezzo(new Attrezzo("Attrezzo Test", 2));
		assertTrue(this.stanza.hasAttrezzo("Attrezzo Test"));
	}
	@Test
	public void testAggiuntaAttrezzoNull() {
		this.stanza.addAttrezzo(null);
		assertEquals(null, this.stanza.getAttrezzi()[0]);
	}

	@Test
	public void testAggiuntaAttrezzoSoglia() {
		this.stanza.addAttrezzo(new Attrezzo("Attrezzo normale", 2));
		this.stanza.addAttrezzo(new Attrezzo("Attrezzo modificato", 2));
		assertEquals(2, this.stanza.getAttrezzo("Attrezzo normale").getPeso());
		assertEquals(4, this.stanza.getAttrezzo("otacifidom ozzerttA").getPeso());
		
	}
}
