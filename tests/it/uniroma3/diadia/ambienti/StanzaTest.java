package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.*;

public class StanzaTest {
	
	private Stanza stanzaTest; 
	private Attrezzo attrezzoTest; 
	
	@Before
	public void setUp() {
		this.stanzaTest = new Stanza("test"); 
		this.attrezzoTest = new Attrezzo("testAttrezzo", 2);
	}
	
	@Test
	public void testRemoveAttrezzoNoAttrezzi() {
		assertFalse(this.stanzaTest.removeAttrezzo(attrezzoTest)); 
	}
	
	@Test
	public void testGetAttrezzoStringaVuota() {
		this.stanzaTest.addAttrezzo(attrezzoTest); 
		assertNull(this.stanzaTest.getAttrezzo("")); 
	}
	
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
