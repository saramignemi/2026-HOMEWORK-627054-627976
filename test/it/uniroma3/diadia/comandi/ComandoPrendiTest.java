package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOFake;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

    private ComandoPrendi prendi;
    private Partita partita;

    @Before
    public void setUp() {
        this.prendi = new ComandoPrendi();
        this.prendi.setIO(new IOFake());
        this.partita = new Partita();
    }

    @Test
    public void testPrendiAttrezzo() {
        partita.getStanzaCorrente().addAttrezzo(new Attrezzo("test", 1));

        prendi.setParametro("test");
        prendi.esegui(partita);

        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("test"));
    }

    @Test
    public void testBorsaPiena() {

        partita.getStanzaCorrente().addAttrezzo(new Attrezzo("test", 1));

        for (int i = 0; i < 10; i++) {
            partita.getGiocatore()
                .getBorsa()
                .addAttrezzo(new Attrezzo("x" + i, 1));
        }

        prendi.setParametro("test");
        prendi.esegui(partita);

        assertEquals(10, partita.getGiocatore().getBorsa().getPeso());
    }

    @Test
    public void testParametroNull() {

        partita.getStanzaCorrente()
            .addAttrezzo(new Attrezzo("test", 1));

        int prima = partita.getStanzaCorrente().getAttrezzi().size();

        prendi.setParametro(null);
        prendi.esegui(partita);

        assertEquals(prima, partita.getStanzaCorrente().getAttrezzi().size());
    }
}