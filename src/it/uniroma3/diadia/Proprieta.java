package it.uniroma3.diadia;

import java.io.*;
import java.util.Properties;

public class Proprieta {

    private static final String NOME_FILE = "diadia.properties";

    private static final int CFU_INIZIALI_DEFAULT   = 20;
    private static final int PESO_MAX_BORSA_DEFAULT = 10;
    private static final int SOGLIA_MAGICA_DEFAULT  = 3;

    private static Proprieta istanza;
    private Properties props;

    private Proprieta() {
        this.props = new Properties();
        // 1. try loading from the filesystem (next to the .jar)
        File file = new File(NOME_FILE);
        if (file.exists()) {
            try (InputStream in = new FileInputStream(file)) {
                props.load(in);
                return;
            } catch (IOException e) {
                System.err.println("Errore lettura " + NOME_FILE + " da filesystem: " + e.getMessage());
            }
        }
        // 2. fallback: load from classpath (inside the .jar)
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(NOME_FILE)) {
            if (in != null) {
                props.load(in);
            } else {
                System.err.println("File " + NOME_FILE + " non trovato, si usano i valori di default.");
            }
        } catch (IOException e) {
            System.err.println("Errore lettura " + NOME_FILE + " da classpath: " + e.getMessage());
        }
    }

    public static Proprieta getInstance() {
        if (istanza == null)
            istanza = new Proprieta();
        return istanza;
    }

    public int getCfuIniziali() {
        return getInt("cfu.iniziali", CFU_INIZIALI_DEFAULT);
    }

    public int getPesoMaxBorsa() {
        return getInt("borsa.peso.max", PESO_MAX_BORSA_DEFAULT);
    }

    public int getSogliaMagica() {
        return getInt("stanza.magica.soglia", SOGLIA_MAGICA_DEFAULT);
    }

    private int getInt(String key, int defaultValue) {
        String val = props.getProperty(key);
        if (val == null) return defaultValue;
        try {
            return Integer.parseInt(val.trim());
        } catch (NumberFormatException e) {
            System.err.println("Valore non valido per " + key + ": " + val + ", uso default " + defaultValue);
            return defaultValue;
        }
    }
}