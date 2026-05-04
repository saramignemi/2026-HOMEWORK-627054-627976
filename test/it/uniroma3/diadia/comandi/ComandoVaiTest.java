package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.Before;

public class ComandoVaiTest {
	private ComandoVai vai;
	private Partita partita;
	
	@Before
	public void setUp() {
		this.vai = new ComandoVai(new IOConsole());
		this.partita = new Partita();
	}
	
	
	@Test
	public void testDirezioneInesistente() {
		vai.setParametro(null);
		Stanza stanzaIniziale = this.partita.getStanzaCorrente();
		vai.esegui(this.partita);
		assertEquals(stanzaIniziale, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testStanzaEsistente() {
		vai.setParametro("est");
		vai.esegui(this.partita);
		assertEquals("Aula N11", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVittoria() {
		vai.setParametro("nord");
		vai.esegui(this.partita);
		assertTrue(this.partita.isFinita());
	}

}
