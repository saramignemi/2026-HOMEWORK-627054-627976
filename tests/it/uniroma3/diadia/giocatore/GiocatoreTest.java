package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import org.junit.Test;

public class GiocatoreTest {

	private Giocatore giocatore; 
	
	@Test
	public void testCostruttoreNoArgs() {
		this.giocatore = new Giocatore(); 
		assertEquals("I cfu sono sbagliati", 20, this.giocatore.getCfu()); 
		assertEquals("Il peso max è sbagliato", 10, this.giocatore.getBorsa().getPesoMax()); 
	}

	@Test
	public void testCostruttore1Args() {
		this.giocatore = new Giocatore(12); 
		assertEquals("I cfu sono sbagliati", 12, this.giocatore.getCfu()); 
		assertEquals("Il peso max è sbagliato", 10, this.giocatore.getBorsa().getPesoMax()); 
	}
	
	@Test
	public void testCostruttore2Args() {
		this.giocatore = new Giocatore(15, 9); 
		assertEquals("I cfu sono sbagliati", 15, this.giocatore.getCfu()); 
		assertEquals("Il peso max è sbagliato", 9, this.giocatore.getBorsa().getPesoMax()); 
	}	
}
