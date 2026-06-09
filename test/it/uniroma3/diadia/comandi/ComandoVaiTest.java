package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class ComandoVaiTest {

    private ComandoVai vai;

    @Before
    public void setUp() {
        this.vai = new ComandoVai();
        this.vai.setIO(new IOConsole());
    }

    @Test
    public void testMonolocale() {

        Partita partita = new Partita(
            new LabirintoBuilder()
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
            new LabirintoBuilder()
                .addStanzaIniziale("Atrio")
                .addStanza("Aula")
                .addStanzaVincente("Aula")
                .addAdiacenza("Atrio", "Aula", "est")
                .addAdiacenza("Aula", "Atrio", "ovest")
                .getLabirinto()
        );

        vai.setParametro("est");
        vai.esegui(partita);

        assertEquals("Aula", partita.getStanzaCorrente().getNome());
    }

    @Test
    public void testTrilocale() {

        Partita partita = new Partita(
            new LabirintoBuilder()
                .addStanzaIniziale("Atrio")
                .addStanza("Aula")
                .addStanzaVincente("Biblioteca")
                .addAdiacenza("Atrio", "Aula", "est")
                .addAdiacenza("Aula", "Biblioteca", "nord")
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