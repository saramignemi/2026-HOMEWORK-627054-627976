package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {
	static final private String NOME_ATTREZZO_APERTURA_DEFAULT = "passepartout";
	private String nomeAttrezzoApertura;
	private String direzioneBloccata;
	
	public StanzaBloccata(String nome, String direzioneBloccata) {
		this(nome, NOME_ATTREZZO_APERTURA_DEFAULT, direzioneBloccata);
	}
	
	public StanzaBloccata(String nome, String nomeAttrezzo, String direzioneBloccata) {
		super(nome);
		this.nomeAttrezzoApertura = nomeAttrezzo;
		this.direzioneBloccata = direzioneBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzione == null | (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(nomeAttrezzoApertura))) return this;
		else return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.getNome());
    	risultato.append("\nUscite: ");
    	for (String direzione : this.getDirezioni())
    		if (direzione!=null)
    			if (direzione.equals(this.direzioneBloccata))
    				if (this.hasAttrezzo(this.nomeAttrezzoApertura))
    					risultato.append(" sbloccata[" + direzione + "]");
    				else risultato.append(" bloccata[" + direzione + "] -> richiede " + this.nomeAttrezzoApertura + ",");
    			else risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.getAttrezzi()) {
    		if (attrezzo != null) {
    			risultato.append(attrezzo.toString()+" ");
    		}
    	}
    	return risultato.toString();
	}
}
