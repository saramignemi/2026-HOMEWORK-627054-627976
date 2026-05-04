package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Classe di test per {@link Giocatore giocatore},
 * verifica il corretto funzionamento dei costruttori
 * e dei valori iniziali di CFU e peso massimo della borsa
 *
 * @author Sara Mignemi
 * @author Juan L. Perez G.
 * @see Giocatore
 * @see Borsa
 * @version base
 */
public class GiocatoreTest {

	private Giocatore giocatore;

	/**
	 * Verifica che il costruttore senza argomenti inizializzi
	 * correttamente i CFU a 20 e il peso massimo della borsa a 10
	 */
	@Test
	public void testCostruttoreNoArgs() {
		this.giocatore = new Giocatore();
		assertEquals("I cfu sono sbagliati", 20, this.giocatore.getCfu());
		assertEquals("Il peso max è sbagliato", 10, this.giocatore.getBorsa().getPesoMax());
	}

	/**
	 * Verifica che il costruttore con un argomento inizializzi
	 * correttamente i CFU al valore passato e il peso massimo della borsa a 10
	 * @param cfu Il numero di CFU da impostare al giocatore
	 */
	@Test
	public void testCostruttore1Args() {
		this.giocatore = new Giocatore(12);
		assertEquals("I cfu sono sbagliati", 12, this.giocatore.getCfu());
		assertEquals("Il peso max è sbagliato", 10, this.giocatore.getBorsa().getPesoMax());
	}

	/**
	 * Verifica che il costruttore con due argomenti inizializzi
	 * correttamente i CFU e il peso massimo della borsa ai valori passati
	 * @param cfu Il numero di CFU da impostare al giocatore
	 * @param pesoMax Il peso massimo da impostare alla borsa
	 */
	@Test
	public void testCostruttore2Args() {
		this.giocatore = new Giocatore(15, 9);
		assertEquals("I cfu sono sbagliati", 15, this.giocatore.getCfu());
		assertEquals("Il peso max è sbagliato", 9, this.giocatore.getBorsa().getPesoMax());
	}
}