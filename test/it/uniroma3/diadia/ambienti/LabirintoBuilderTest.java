package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LabirintoBuilderTest {

    private Labirinto.LabirintoBuilder builder;

    @Before
    public void setUp() {
        builder = Labirinto.newBuilder();
    }

    // ------------------------------------------------------------------ //
    //  factory method & costruttore privato
    // ------------------------------------------------------------------ //

    @Test
    public void testNewBuilderNonNull() {
        assertNotNull(Labirinto.newBuilder());
    }

    @Test
    public void testCostruttorePrivato() throws NoSuchMethodException {
        var ctor = Labirinto.class.getDeclaredConstructor(Stanza.class, Stanza.class);
        assertFalse(ctor.canAccess(null));
    }

    // ------------------------------------------------------------------ //
    //  stanze iniziale e vincente
    // ------------------------------------------------------------------ //

    @Test
    public void testStanzaInizialeImpostata() {
        Labirinto lab = builder
            .addStanzaIniziale("Atrio")
            .addStanzaVincente("Biblioteca")
            .getLabirinto();
        assertEquals("Atrio", lab.getStanzaIniziale().getNome());
    }

    @Test
    public void testStanzaVincenteImpostata() {
        Labirinto lab = builder
            .addStanzaIniziale("Atrio")
            .addStanzaVincente("Biblioteca")
            .getLabirinto();
        assertEquals("Biblioteca", lab.getStanzaVincente().getNome());
    }

    @Test
    public void testStanzaCorrenteUgualeAIniziale() {
        Labirinto lab = builder
            .addStanzaIniziale("Atrio")
            .addStanzaVincente("Biblioteca")
            .getLabirinto();
        assertEquals(lab.getStanzaIniziale(), lab.getStanzaCorrente());
    }

    // ------------------------------------------------------------------ //
    //  adiacenze
    // ------------------------------------------------------------------ //

    @Test
    public void testAdiacenzaImpostata() {
        Labirinto lab = builder
            .addStanzaIniziale("Atrio")
            .addStanzaVincente("Biblioteca")
            .addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
            .getLabirinto();
        assertEquals("Biblioteca",
            lab.getStanzaIniziale().getStanzaAdiacente(Direzione.nord).getNome());
    }

    @Test
    public void testAdiacenzaInesistenteDirezioneNull() {
        Labirinto lab = builder
            .addStanzaIniziale("Atrio")
            .addStanzaVincente("Biblioteca")
            .getLabirinto();
        assertNull(lab.getStanzaIniziale().getStanzaAdiacente(Direzione.sud));
    }

    @Test
    public void testAdiacenzaStanzaInesistenteIgnorata() {
        // addAdiacenza con stanza destinazione non registrata non deve crashare
        Labirinto lab = builder
            .addStanzaIniziale("Atrio")
            .addAdiacenza("Atrio", "Fantasma", Direzione.est)
            .addStanzaVincente("Atrio")
            .getLabirinto();
        assertNull(lab.getStanzaIniziale().getStanzaAdiacente(Direzione.est));
    }

    // ------------------------------------------------------------------ //
    //  attrezzi
    // ------------------------------------------------------------------ //

    @Test
    public void testAttrezzoAggiunto() {
        Labirinto lab = builder
            .addStanzaIniziale("Atrio")
            .addStanzaVincente("Atrio")
            .inStanza("Atrio").addAttrezzo("lanterna", 3)
            .getLabirinto();
        assertTrue(lab.getStanzaIniziale().hasAttrezzo("lanterna"));
    }

    @Test
    public void testAttrezzoNonPresente() {
        Labirinto lab = builder
            .addStanzaIniziale("Atrio")
            .addStanzaVincente("Atrio")
            .getLabirinto();
        assertFalse(lab.getStanzaIniziale().hasAttrezzo("spada"));
    }

    // ------------------------------------------------------------------ //
    //  tipi di stanza speciali
    // ------------------------------------------------------------------ //

    @Test
    public void testStanzaBuiaCreata() {
        Labirinto lab = builder
            .addStanzaBuia("Cantina", "lanterna")
            .addStanzaIniziale("Cantina")
            .addStanzaVincente("Cantina")
            .getLabirinto();
        assertTrue(lab.getStanzaIniziale() instanceof StanzaBuia);
    }

    @Test
    public void testStanzaBloccataCreata() {
        Labirinto lab = builder
            .addStanzaBloccata("Atrio", Direzione.nord)
            .addStanzaIniziale("Atrio")
            .addStanzaVincente("Atrio")
            .getLabirinto();
        assertTrue(lab.getStanzaIniziale() instanceof StanzaBloccata);
    }

    @Test
    public void testStanzaMagicaCreata() {
        Labirinto lab = builder
            .addStanzaMagica("Lab", 2)
            .addStanzaIniziale("Lab")
            .addStanzaVincente("Lab")
            .getLabirinto();
        assertTrue(lab.getStanzaIniziale() instanceof StanzaMagica);
    }

    // ------------------------------------------------------------------ //
    //  ogni builder produce un'istanza indipendente
    // ------------------------------------------------------------------ //

    @Test
    public void testDueLabirintiIndipendenti() {
        Labirinto lab1 = Labirinto.newBuilder()
            .addStanzaIniziale("A").addStanzaVincente("A").getLabirinto();
        Labirinto lab2 = Labirinto.newBuilder()
            .addStanzaIniziale("B").addStanzaVincente("B").getLabirinto();
        assertNotEquals(lab1.getStanzaIniziale().getNome(),
                        lab2.getStanzaIniziale().getNome());
    }
}