package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.*;

/**
 * Classe di test per {@link Stanza stanza},
 * verifica il corretto funzionamento dell'aggiunta e rimozione
 * di attrezzi e della ricerca per nome
 *
 * @author Sara Mignemi
 * @author Juan L. Perez G.
 * @see Stanza
 * @see Attrezzo
 * @version base
 */
public class StanzaTest {

	private Stanza stanzaTest;
	private Attrezzo attrezzoTest;

	@Before
	public void setUp() {
		this.stanzaTest = new Stanza("test");
		this.attrezzoTest = new Attrezzo("testAttrezzo", 2);
	}

	/**
	 * Verifica che la rimozione di un attrezzo da una stanza
	 * senza attrezzi ritorni false
	 */
	@Test
	public void testRemoveAttrezzoNoAttrezzi() {
		assertFalse(this.stanzaTest.removeAttrezzo(attrezzoTest));
	}

	/**
	 * Verifica che la ricerca di un attrezzo con una stringa vuota
	 * ritorni null anche se la stanza contiene attrezzi
	 */
	@Test
	public void testGetAttrezzoStringaVuota() {
		this.stanzaTest.addAttrezzo(attrezzoTest);
		assertNull(this.stanzaTest.getAttrezzo(""));
	}

	/**
	 * Verifica che non sia possibile aggiungere un attrezzo
	 * quando la stanza ha già raggiunto il numero massimo di attrezzi
	 */
	@Test
	public void testAddAttrezzoStanzaPiena() {
		for (int i=0; i<10; i++) {
			this.attrezzoTest = new Attrezzo("testAttrezzo", 2);
			this.stanzaTest.addAttrezzo(attrezzoTest);
		}
		this.attrezzoTest = new Attrezzo("testAttrezzo", 2);
		assertFalse(this.stanzaTest.addAttrezzo(attrezzoTest));
	}
}