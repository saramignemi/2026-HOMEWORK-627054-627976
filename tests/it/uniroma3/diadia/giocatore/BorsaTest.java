package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.*;

public class BorsaTest {
	
	private Borsa borsa; 

	@Before
	public void setUp() {
		this.borsa = new Borsa();
	}

	@Test 
	public void testAddAttrezzoPiena() {
		for (int i=0; i<10; i++) {
			Attrezzo nuovo = new Attrezzo("nuovo", 1);
			this.borsa.addAttrezzo(nuovo); 
		}
		Attrezzo eccesso = new Attrezzo("eccesso", 8); 
		assertFalse(this.borsa.addAttrezzo(eccesso)); 
	}
	
	@Test
	public void testRemoveAttrezzoVuota() {
		assertNull(this.borsa.removeAttrezzo("")); 
	}
	
	@Test
	public void testToStringVuota() {
		assertEquals("Borsa vuota", this.borsa.toString()); 
	}

}
