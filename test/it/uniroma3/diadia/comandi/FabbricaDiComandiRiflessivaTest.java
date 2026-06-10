package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOFake;

public class FabbricaDiComandiRiflessivaTest {

    private FabbricaDiComandiRiflessiva fabbrica;
    private IO io;

    @Before
    public void setUp() {
        this.fabbrica = new FabbricaDiComandiRiflessiva();
        this.io = new IOFake();
    }

    @Test
    public void testComandoValido() throws Exception {
        Comando comando = this.fabbrica.costruisciComando("vai nord", io);
        assertNotNull(comando);
        assertEquals("vai", comando.getNome());
    }

    @Test
    public void testComandoConParametro() throws Exception {
        Comando comando = this.fabbrica.costruisciComando("vai nord", io);
        assertEquals("nord", comando.getParametro());
    }

    @Test
    public void testComandoSenzaParametro() throws Exception {
        Comando comando = this.fabbrica.costruisciComando("aiuto", io);
        assertNotNull(comando);
        assertNull(comando.getParametro());
    }

    @Test
    public void testComandoNonEsistente() throws Exception {
        Comando comando = this.fabbrica.costruisciComando("comandoinesistente", io);
        assertEquals("INVALID", comando.getNome());
    }

    @Test
    public void testComandoVuoto() throws Exception {
        Comando comando = this.fabbrica.costruisciComando("", io);
        assertEquals("INVALID", comando.getNome());
    }

    @Test
    public void testComandoNull() throws Exception {
        Comando comando = this.fabbrica.costruisciComando(null, io);
        assertEquals("INVALID", comando.getNome());
    }
}