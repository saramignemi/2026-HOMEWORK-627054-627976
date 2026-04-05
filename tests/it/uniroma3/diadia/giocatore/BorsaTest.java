package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.*;

/**
 * Classe di test per {@link Borsa borsa},
 * verifica il corretto funzionamento dell'aggiunta e rimozione
 * di attrezzi e della rappresentazione in stringa
 *
 * @author Sara Mignemi
 * @author Juan L. Perez G.
 * @see Borsa
 * @see Attrezzo
 * @version base
 */
public class BorsaTest {

	private Borsa borsa;

	@Before
	public void setUp() {
		this.borsa = new Borsa();
	}

	/**
	 * Verifica che non sia possibile aggiungere un attrezzo
	 * quando la borsa ha già raggiunto il numero massimo di attrezzi
	 */
	@Test
	public void testAddAttrezzoPiena() {
		for (int i=0; i<10; i++) {
			Attrezzo nuovo = new Attrezzo("nuovo", 1);
			this.borsa.addAttrezzo(nuovo);
		}
		Attrezzo eccesso = new Attrezzo("eccesso", 8);
		assertFalse(this.borsa.addAttrezzo(eccesso));
	}

	/**
	 * Verifica che la rimozione di un attrezzo da una borsa vuota
	 * ritorni null
	 */
	@Test
	public void testRemoveAttrezzoVuota() {
		assertNull(this.borsa.removeAttrezzo(""));
	}

	/**
	 * Verifica che la rappresentazione in stringa di una borsa vuota
	 * sia "Borsa vuota"
	 */
	@Test
	public void testToStringVuota() {
		assertEquals("Borsa vuota", this.borsa.toString());
	}
}