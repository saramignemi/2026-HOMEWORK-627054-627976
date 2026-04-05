package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * Classe di test per {@link Labirinto labirinto},
 * verifica il corretto funzionamento della stanza iniziale,
 * della stanza corrente e della stanza vincente
 *
 * @author Sara Mignemi
 * @author Juan L. Perez G.
 * @see Labirinto
 * @see Stanza
 * @version base
 */
public class LabirintoTest {
	private Labirinto labirinto;

	public LabirintoTest() {}

	@Before
	public void setUp() {
		this.labirinto = new Labirinto();
	}

	/**
	 * Verifica che la stanza iniziale del labirinto non sia nulla
	 */
	@Test
	public void testStanzaInizialeNonVuota() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}

	/**
	 * Verifica che la stanza corrente iniziale sia l'Atrio
	 */
	@Test
	public void testGetStanzaCorrente() {
		assertEquals("Atrio", this.labirinto.getStanzaCorrente().getNome());
	}

	/**
	 * Verifica che la stanza vincente sia la Biblioteca
	 */
	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome());
	}

	/**
	 * Verifica che la stanza iniziale e la stanza corrente siano lo stesso oggetto
	 */
	@Test
	public void testStanzaInizialeUgualeStanzaCorrente() {
		assertSame(this.labirinto.getStanzaIniziale(), this.labirinto.getStanzaCorrente());
	}

	/**
	 * Verifica che dopo aver impostato una nuova stanza corrente,
	 * il labirinto la restituisca correttamente
	 */
	@Test
	public void testSetStanzaCorrente() {
		Stanza test = new Stanza("test");
		this.labirinto.setStanzaCorrente(test);
		assertSame(test, this.labirinto.getStanzaCorrente());
	}

}
