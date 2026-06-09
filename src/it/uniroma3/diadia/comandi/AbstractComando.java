package it.uniroma3.diadia.comandi;

import java.util.ArrayList;
import java.util.List;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
    protected IO io;
    private static List<String> nomiComandi = new ArrayList<>();

    public AbstractComando() {
        String nomeClasse = this.getClass().getSimpleName(); // es. "ComandoVai"
        if (nomeClasse.startsWith("Comando")) {
            String nomeComando = nomeClasse.substring("Comando".length())
                                           .toLowerCase(); // es. "vai"
            if (!nomiComandi.contains(nomeComando))
                nomiComandi.add(nomeComando);
        }
    }

    public static List<String> getNomiComandi() {
        return nomiComandi;
    }

    @Override
    public void setIO(IO io) {
        this.io = io;
    }

    @Override
    public void setParametro(String parametro) {
        // default vuoto
    }

    @Override
    public String getParametro() {
        return null;
    }

    @Override
    public abstract void esegui(Partita partita);

    @Override
    public abstract String getNome();
}