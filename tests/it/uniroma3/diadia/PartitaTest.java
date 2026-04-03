package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PartitaTest {
	
	private Partita partita; 
	
	@Before
	public void setUp() {
		this.partita = new Partita(); 
	}
	
	@Test
	public void testIsFinitaVinta() {
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente()); 
		assertTrue(this.partita.isFinita()); 
	}
	
	@Test
	public void testIsFinitaNoCfu() {
		this.partita.setCfu(0); 
		assertTrue(this.partita.isFinita()); 
	}
	
	@Test
	public void testIsFinitaVariabile() {
		this.partita.setFinita(); 
		assertTrue(this.partita.isFinita()); 
	}


}
