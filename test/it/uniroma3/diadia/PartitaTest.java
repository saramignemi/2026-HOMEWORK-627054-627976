package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe di test per {@link Partita partita},
 * verifica il corretto funzionamento delle condizioni
 * di fine partita
 *
 * @author Sara Mignemi
 * @author Juan L. Perez G.
 * @see Partita
 * @version base
 */
public class PartitaTest {

	private Partita partita;

	@Before
	public void setUp() {
		this.partita = new Partita();
	}

	/**
	 * Verifica che la partita sia finita quando il giocatore
	 * si trova nella stanza vincente
	 */
	@Test
	public void testIsFinitaVinta() {
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}

	/**
	 * Verifica che la partita sia finita quando il giocatore
	 * ha esaurito tutti i CFU
	 */
	@Test
	public void testIsFinitaNoCfu() {
		this.partita.setCfu(0);
		assertTrue(this.partita.isFinita());
	}

	/**
	 * Verifica che la partita sia finita quando
	 * la variabile di fine partita è stata impostata
	 */
	@Test
	public void testIsFinitaVariabile() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
}