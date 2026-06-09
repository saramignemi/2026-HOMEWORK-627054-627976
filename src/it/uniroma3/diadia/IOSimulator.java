package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO {

    private List<String> inputs;
    private int index;

    private List<String> messaggi;

    public IOSimulator(List<String> inputs) {
        this.inputs = new ArrayList<>(inputs);
        this.index = 0;
        this.messaggi = new ArrayList<>();
    }

    public String leggiRiga() {
        if (index >= inputs.size())
            return null;
        return inputs.get(index++);
    }

    public void mostraMessaggio(String messaggio) {
        this.messaggi.add(messaggio);
    }

    public String getLastString() {
        if (messaggi.isEmpty()) return null;
        return messaggi.get(messaggi.size() - 1);
    }

    // utile per i test
    public List<String> getMessaggi() {
        return this.messaggi;
    }
}