package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class AbstractComandoTest {

    private static class ComandoFake extends AbstractComando {
        @Override
        public void esegui(Partita partita) {
            io.mostraMessaggio("ok");
        }

        @Override
        public String getNome() {
            return "fake";
        }
    }

    private ComandoFake comando;
    private Partita partita;
    private IO io;

    @Before
    public void setUp() {
        this.comando = new ComandoFake();
        this.io = new IOConsole();
        this.comando.setIO(io);
        this.partita = new Partita();
    }

    @Test
    public void testSetParametroNonInfluisce() {
        comando.setParametro("qualcosa");
        assertNull(comando.getParametro());
    }

    @Test
    public void testSetIONonNull() {
        comando.esegui(partita); // non deve crashare
        assertTrue(true);
    }
}