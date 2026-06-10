package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOFake;

public class FabbricaDiComandiFisarmonicaTest {
	private FabbricaDiComandiFisarmonica fabbrica;
	private Comando comando;
	@Before
	public void setUp(){
		this.fabbrica = new FabbricaDiComandiFisarmonica();
	}

	@Test
	public void testNomeComandoCorrettoConParametro() {
		this.comando = fabbrica.costruisciComando("vai sud", new IOFake());
		assertEquals("vai", this.comando.getNome());
		assertEquals("sud", this.comando.getParametro());
	}
	@Test
	public void testComandoCorrettoParametroNull() {
		this.comando = fabbrica.costruisciComando("vai", new IOFake());
		assertEquals("vai", this.comando.getNome());
		assertNull(this.comando.getParametro());

	}
	@Test
	public void testNomeComandoInesistente() {
		this.comando = fabbrica.costruisciComando("non esisto", new IOFake());
		assertEquals("INVALID", this.comando.getNome());
		assertNull(this.comando.getParametro());

	}

}
