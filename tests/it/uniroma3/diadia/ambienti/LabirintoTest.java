package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before; 

public class LabirintoTest {
	private Labirinto labirinto; 
	
	public LabirintoTest() {}
	
	@Before
	public void setUp() {
		this.labirinto = new Labirinto(); 
	}
	
	@Test
	public void testStanzaInizialeNonVuota() {
		assertNotNull(this.labirinto.getStanzaIniziale()); 
	}
	
	@Test
	public void testGetStanzaCorrente() {
		assertEquals("Atrio", this.labirinto.getStanzaCorrente().getNome()); 
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome()); 
	}
	
	@Test 
	public void testStanzaInizialeUgualeStanzaCorrente() {
		assertSame(this.labirinto.getStanzaIniziale(), this.labirinto.getStanzaCorrente()); 
	}
	
	@Test
	public void testSetStanzaCorrente() {
		Stanza test = new Stanza("test");
		this.labirinto.setStanzaCorrente(test); 
		assertSame(test, this.labirinto.getStanzaCorrente()); 
	}
	
	

}
