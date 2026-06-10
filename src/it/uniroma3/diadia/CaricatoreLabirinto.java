package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.*;

public class CaricatoreLabirinto {

    private static final String STANZE_MARKER          = "Stanze:";
    private static final String BUIE_MARKER            = "Buie:";
    private static final String BLOCCATE_MARKER        = "Bloccate:";
    private static final String STANZA_INIZIALE_MARKER = "Inizio:";
    private static final String STANZA_VINCENTE_MARKER = "Vincente:";
    private static final String ATTREZZI_MARKER        = "Attrezzi:";
    private static final String USCITE_MARKER          = "Uscite:";
    private static final String PERSONAGGI_MARKER      = "Personaggi:";

    private LineNumberReader reader;
    private Labirinto.LabirintoBuilder builder;    
    

    public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
        this.reader = new LineNumberReader(new FileReader(nomeFile));
        this.builder = Labirinto.newBuilder();    }

    public Labirinto carica() throws FormatoFileNonValidoException {
        try {
            leggiECreaStanze();
            leggiECreaStanzeBuie();
            leggiECreaStanzeBloccate();
            leggiInizialeEvincente();
            leggiECollocaAttrezzi();
            leggiEImpostaUscite();
            leggiECollocaPersonaggi();
            return builder.getLabirinto();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void leggiECreaStanze() throws FormatoFileNonValidoException {
        String line = leggiRigaCheCominciaPer(STANZE_MARKER);
        if (line.isBlank()) return;
        for (String nome : separaAlleVirgole(line))
            builder.addStanza(nome);
    }

    private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
        String line = leggiRigaCheCominciaPer(BUIE_MARKER);
        if (line.isBlank()) return;
        for (String token : separaAlleVirgole(line)) {
            Scanner sc = new Scanner(token.trim());
            check(sc.hasNext(), "Nome stanza buia mancante");
            String nome = sc.next();
            if (sc.hasNext())
                builder.addStanzaBuia(nome, sc.next());
            else
                builder.addStanzaBuia(nome);
            sc.close();
        }
    }

    private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
        String line = leggiRigaCheCominciaPer(BLOCCATE_MARKER);
        if (line.isBlank()) return;
        for (String token : separaAlleVirgole(line)) {
            Scanner sc = new Scanner(token.trim());
            check(sc.hasNext(), "Nome stanza bloccata mancante");
            String nome = sc.next();
            check(sc.hasNext(), "Direzione bloccata mancante per stanza " + nome);
            Direzione direzione = parseDirezione(sc.next(), nome);
            if (sc.hasNext())
                builder.addStanzaBloccata(nome, sc.next(), direzione);
            else
                builder.addStanzaBloccata(nome, direzione);
            sc.close();
        }
    }

    private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
        String nomeIniziale = leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER).trim();
        check(builder.getListaStanze().containsKey(nomeIniziale), nomeIniziale + " non definita");
        builder.addStanzaIniziale(nomeIniziale);

        String nomeVincente = leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER).trim();
        check(builder.getListaStanze().containsKey(nomeVincente), nomeVincente + " non definita");
        builder.addStanzaVincente(nomeVincente);
    }

    private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
        String line = leggiRigaCheCominciaPer(ATTREZZI_MARKER);
        if (line.isBlank()) return;
        for (String token : separaAlleVirgole(line)) {
            Scanner sc = new Scanner(token.trim());
            check(sc.hasNext(), msgPrecoce("nome attrezzo"));
            String nome = sc.next();
            check(sc.hasNext(), msgPrecoce("peso attrezzo " + nome));
            String pesoStr = sc.next();
            check(sc.hasNext(), msgPrecoce("stanza per attrezzo " + nome));
            String nomeStanza = sc.next();
            sc.close();
            try {
                int peso = Integer.parseInt(pesoStr.trim());
                check(builder.getListaStanze().containsKey(nomeStanza),
                        "Stanza inesistente per attrezzo " + nome + ": " + nomeStanza);
                builder.inStanza(nomeStanza).addAttrezzo(nome, peso);
            } catch (NumberFormatException e) {
                check(false, "Peso non valido per attrezzo " + nome + ": " + pesoStr);
            }
        }
    }

    private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
        String line = leggiRigaCheCominciaPer(USCITE_MARKER);
        if (line.isBlank()) return;
        for (String token : separaAlleVirgole(line)) {
            Scanner sc = new Scanner(token.trim());
            check(sc.hasNext(), msgPrecoce("stanza di partenza"));
            String da = sc.next();
            check(sc.hasNext(), msgPrecoce("direzione da " + da));
            Direzione dir = parseDirezione(sc.next(), da);
            check(sc.hasNext(), msgPrecoce("destinazione da " + da + " " + dir));
            String a = sc.next();
            sc.close();
            check(builder.getListaStanze().containsKey(da), "Stanza di partenza sconosciuta: " + da);
            check(builder.getListaStanze().containsKey(a),  "Stanza di destinazione sconosciuta: " + a);
            builder.addAdiacenza(da, a, dir);
        }
    }

    private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
        String line = leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
        if (line.isBlank()) return;
        for (String token : separaAlleVirgole(line)) {
            Scanner sc = new Scanner(token.trim());
            check(sc.hasNext(), msgPrecoce("tipo personaggio"));
            String tipo = sc.next().toLowerCase();
            switch (tipo) {
                case "cane"   -> leggiCane(sc);
                case "mago"   -> leggiMago(sc);
                case "strega" -> leggiStrega(sc);
                default       -> check(false, "Tipo personaggio sconosciuto: " + tipo);
            }
            sc.close();
        }
    }

    private void leggiCane(Scanner sc) throws FormatoFileNonValidoException {
        check(sc.hasNext(), msgPrecoce("nome cane"));
        String nome = sc.next();
        check(sc.hasNext(), msgPrecoce("stanza cane " + nome));
        String nomeStanza = sc.next();
        check(sc.hasNext(), msgPrecoce("nome attrezzo cane " + nome));
        String nomeAttrezzo = sc.next();
        check(sc.hasNext(), msgPrecoce("peso attrezzo cane " + nome));
        String pesoStr = sc.next();
        check(sc.hasNext(), msgPrecoce("cibo cane " + nome));
        String cibo = sc.next();
        check(builder.getListaStanze().containsKey(nomeStanza),
                "Stanza sconosciuta per cane " + nome + ": " + nomeStanza);
        try {
            int peso = Integer.parseInt(pesoStr.trim());
            builder.inStanza(nomeStanza).addCane(nome, nomeAttrezzo, peso, cibo);
        } catch (NumberFormatException e) {
            check(false, "Peso non valido per attrezzo cane " + nome + ": " + pesoStr);
        }
    }

    private void leggiMago(Scanner sc) throws FormatoFileNonValidoException {
        check(sc.hasNext(), msgPrecoce("nome mago"));
        String nome = sc.next();
        check(sc.hasNext(), msgPrecoce("stanza mago " + nome));
        String nomeStanza = sc.next();
        check(builder.getListaStanze().containsKey(nomeStanza),
                "Stanza sconosciuta per mago " + nome + ": " + nomeStanza);
        builder.inStanza(nomeStanza).addMago(nome);
    }

    private void leggiStrega(Scanner sc) throws FormatoFileNonValidoException {
        check(sc.hasNext(), msgPrecoce("nome strega"));
        String nome = sc.next();
        check(sc.hasNext(), msgPrecoce("stanza strega " + nome));
        String nomeStanza = sc.next();
        check(builder.getListaStanze().containsKey(nomeStanza),
                "Stanza sconosciuta per strega " + nome + ": " + nomeStanza);
        builder.inStanza(nomeStanza).addStrega(nome);
    }

    // ------------------------------------------------------------------ //
    //  UTILITY
    // ------------------------------------------------------------------ //

    /**
     * Parses a direction string into a Direzione enum value,
     * throwing a FormatoFileNonValidoException with a clear message if invalid.
     */
    private Direzione parseDirezione(String s, String contesto) throws FormatoFileNonValidoException {
        try {
            return Direzione.valueOf(s.trim());
        } catch (IllegalArgumentException e) {
            check(false, "Direzione non valida \"" + s + "\" (contesto: " + contesto + "). "
                    + "Valori ammessi: " + Arrays.toString(Direzione.values()));
            return null; // unreachable, check() always throws
        }
    }

    private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
        try {
            String riga = reader.readLine();
            check(riga != null, "File terminato prima del marker: " + marker);
            check(riga.startsWith(marker), "Attesa riga che inizia con \"" + marker + "\"");
            return riga.substring(marker.length());
        } catch (IOException e) {
            throw new FormatoFileNonValidoException(e.getMessage());
        }
    }

    private List<String> separaAlleVirgole(String s) {
        List<String> result = new LinkedList<>();
        Scanner sc = new Scanner(s);
        sc.useDelimiter(",");
        while (sc.hasNext()) result.add(sc.next().trim());
        sc.close();
        return result;
    }

    private void check(boolean cond, String msg) throws FormatoFileNonValidoException {
        if (!cond)
            throw new FormatoFileNonValidoException(
                "Formato file non valido [" + reader.getLineNumber() + "] " + msg);
    }

    private String msgPrecoce(String cosa) {
        return "Terminazione precoce prima di leggere: " + cosa;
    }
}