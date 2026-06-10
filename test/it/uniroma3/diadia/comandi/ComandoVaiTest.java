package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOFake;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

public class ComandoVaiTest {

    private ComandoVai vai;

    @Before
    public void setUp() {
        this.vai = new ComandoVai();
        this.vai.setIO(new IOFake());
    }

    @Test
    public void testMonolocale() {

        Partita partita = new Partita(
            Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addStanzaVincente("Atrio")
                .getLabirinto()
        );

        vai.setParametro("nord");
        vai.esegui(partita);

        assertEquals("Atrio", partita.getStanzaCorrente().getNome());
    }

    @Test
    public void testBilocale() {

        Partita partita = new Partita(
            Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addStanza("Aula")
                .addStanzaVincente("Aula")
                .addAdiacenza("Atrio", "Aula", Direzione.est)
                .addAdiacenza("Aula", "Atrio", Direzione.ovest)
                .getLabirinto()
        );

        vai.setParametro("est");
        vai.esegui(partita);

        assertEquals("Aula", partita.getStanzaCorrente().getNome());
    }

    @Test
    public void testTrilocale() {

        Partita partita = new Partita(
            Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addStanza("Aula")
                .addStanzaVincente("Biblioteca")
                .addAdiacenza("Atrio", "Aula", Direzione.est)
                .addAdiacenza("Aula", "Biblioteca", Direzione.nord)
                .getLabirinto()
        );

        vai.setParametro("est");
        vai.esegui(partita);

        vai.setParametro("nord");
        vai.esegui(partita);

        assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
        assertTrue(partita.isFinita());
    }
}