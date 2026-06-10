package it.uniroma3.diadia;

public class IOFake implements IO {

    @Override
    public void mostraMessaggio(String msg) {
        // silenzioso nei test
    }

    @Override
    public String leggiRiga() {
        return "";
    }
}