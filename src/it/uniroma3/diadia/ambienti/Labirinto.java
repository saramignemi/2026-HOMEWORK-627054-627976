package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

/**
 * Rappresenta il labirinto della partita.
 * Il costruttore è privato: usare Labirinto.newBuilder() per costruirlo.
 */
public class Labirinto {

    private Stanza stanzaIniziale;
    private Stanza stanzaVincente;
    private Stanza stanzaCorrente;

    // costruttore privato: solo LabirintoBuilder può istanziarlo
    private Labirinto(Stanza stanzaIniziale, Stanza stanzaVincente) {
        this.stanzaIniziale = stanzaIniziale;
        this.stanzaVincente = stanzaVincente;
        this.stanzaCorrente = stanzaIniziale;
    }

    /** Factory method: unico punto di ingresso per costruire un Labirinto */
    public static LabirintoBuilder newBuilder() {
        return new LabirintoBuilder();
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public Stanza getStanzaCorrente()  { return this.stanzaCorrente; }
    public Stanza getStanzaVincente()  { return this.stanzaVincente; }
    public Stanza getStanzaIniziale()  { return this.stanzaIniziale; }

    // ================================================================== //
    //  NESTED BUILDER
    //
    //  Dichiarata PUBLIC perché:
    //  - chi costruisce un Labirinto (CaricatoreLabirinto, test, main)
    //    deve poter chiamare i metodi add* sul builder
    //  - se fosse private sarebbe inaccessibile fuori da Labirinto
    //  - non è un problema di incapsulamento: il costruttore di Labirinto
    //    resta privato, quindi l'unico modo di ottenere un Labirinto
    //    è sempre passare dal builder
    // ================================================================== //
    public static class LabirintoBuilder {

        private Map<String, Stanza>                    stanze;
        private Map<String, Map<Direzione, String>>    adiacenze;
        private Map<String, Map<String, Attrezzo>>     attrezzi;
        private Map<String, AbstractPersonaggio>       personaggi;
        private Stanza stanzaVincente;
        private Stanza stanzaIniziale;
        private String ultimaStanza;

        // costruttore package-private: si ottiene solo via Labirinto.newBuilder()
        LabirintoBuilder() {
            this.stanze     = new HashMap<>();
            this.adiacenze  = new HashMap<>();
            this.attrezzi   = new HashMap<>();
            this.personaggi = new HashMap<>();
        }

        public Labirinto getLabirinto() {
            for (String s : this.stanze.keySet()) {
                this.stanze.get(s).setAttrezzi(this.attrezzi.get(s));
                Map<Direzione, String> adiacenza = this.adiacenze.get(s);
                if (adiacenza != null)
                    for (Direzione d : adiacenza.keySet())
                        this.stanze.get(s).impostaStanzaAdiacente(d, this.stanze.get(adiacenza.get(d)));
                this.stanze.get(s).setPersonaggio(this.personaggi.get(s));
            }
            return new Labirinto(this.stanzaIniziale, this.stanzaVincente);
        }

        private void creaSeNonEsiste(String nome, Stanza stanza) {
            stanze.putIfAbsent(nome, stanza);
            adiacenze.putIfAbsent(nome, new HashMap<>());
            attrezzi.putIfAbsent(nome, new HashMap<>());
        }

        public LabirintoBuilder addStanzaIniziale(String nome) {
            this.addStanza(nome);
            this.stanzaIniziale = this.stanze.get(nome);
            return this;
        }

        public LabirintoBuilder addStanzaVincente(String nome) {
            this.addStanza(nome);
            this.stanzaVincente = this.stanze.get(nome);
            return this;
        }

        public LabirintoBuilder addStanza(String nome) {
            this.ultimaStanza = nome;
            this.creaSeNonEsiste(nome, new Stanza(nome));
            return this;
        }

        public LabirintoBuilder addStanzaBloccata(String nome, Direzione direzione) {
            this.ultimaStanza = nome;
            this.creaSeNonEsiste(nome, new StanzaBloccata(nome, direzione));
            return this;
        }

        public LabirintoBuilder addStanzaBloccata(String nome, String attrezzo, Direzione direzione) {
            this.ultimaStanza = nome;
            this.creaSeNonEsiste(nome, new StanzaBloccata(nome, attrezzo, direzione));
            return this;
        }

        public LabirintoBuilder addStanzaBuia(String nome) {
            this.ultimaStanza = nome;
            this.creaSeNonEsiste(nome, new StanzaBuia(nome));
            return this;
        }

        public LabirintoBuilder addStanzaBuia(String nome, String attrezzo) {
            this.ultimaStanza = nome;
            this.creaSeNonEsiste(nome, new StanzaBuia(nome, attrezzo));
            return this;
        }

        public LabirintoBuilder addStanzaMagica(String nome) {
            this.ultimaStanza = nome;
            this.creaSeNonEsiste(nome, new StanzaMagica(nome));
            return this;
        }

        public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
            this.ultimaStanza = nome;
            this.creaSeNonEsiste(nome, new StanzaMagica(nome, soglia));
            return this;
        }

        public LabirintoBuilder addAttrezzo(String nome, int peso) {
            if (this.ultimaStanza == null) return this;
            this.attrezzi.get(this.ultimaStanza).put(nome, new Attrezzo(nome, peso));
            return this;
        }

        public LabirintoBuilder addAdiacenza(String origin, String exit, Direzione dir) {
            if (this.stanze.get(origin) == null || this.stanze.get(exit) == null) return this;
            this.adiacenze.get(origin).put(dir, exit);
            return this;
        }

        public LabirintoBuilder inStanza(String nome) {
            this.ultimaStanza = nome;
            return this;
        }

        public Map<String, Stanza> getListaStanze() {
            return this.stanze;
        }

        public LabirintoBuilder addCane(String nome, String attrezzo, int peso, String cibo) {
            if (this.ultimaStanza == null) return this;
            this.personaggi.put(this.ultimaStanza, new Cane(nome, new Attrezzo(attrezzo, peso), cibo));
            return this;
        }

        public LabirintoBuilder addMago(String nome) {
            if (this.ultimaStanza == null) return this;
            this.personaggi.put(this.ultimaStanza, new Mago(nome, "Libro", 2));
            return this;
        }

        public LabirintoBuilder addStrega(String nome) {
            if (this.ultimaStanza == null) return this;
            this.personaggi.put(this.ultimaStanza, new Strega(nome));
            return this;
        }

        public LabirintoBuilder addPersonaggio(AbstractPersonaggio p) {
            if (this.ultimaStanza == null) return this;
            this.personaggi.put(this.ultimaStanza, p);
            return this;
        }
    }
}