package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOFake;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {

    private ComandoPosa posa;
    private Partita partita;

    @Before
    public void setUp() {
        this.posa = new ComandoPosa();
        this.posa.setIO(new IOFake());
        this.partita = new Partita();

        this.partita.getGiocatore()
            .getBorsa()
            .addAttrezzo(new Attrezzo("test", 1));
    }

    @Test
    public void testPosaAttrezzo() {
        posa.setParametro("test");
        posa.esegui(partita);

        assertTrue(partita.getGiocatore().getBorsa().isEmpty());
    }

    @Test
    public void testParametroNullNonRimuove() {
        posa.setParametro(null);
        posa.esegui(partita);

        assertFalse(partita.getGiocatore().getBorsa().isEmpty());
    }

    @Test
    public void testAttrezzoNonPresente() {
        posa.setParametro("ciao");
        posa.esegui(partita);

        assertFalse(partita.getGiocatore().getBorsa().isEmpty());
    }
}