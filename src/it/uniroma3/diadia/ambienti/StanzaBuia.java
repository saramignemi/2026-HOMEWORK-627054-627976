package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private String nomeAttrezzoIlluminante;
	static final private String ATTREZZO_ILLUMINANTE_DEFAULT = "lanterna";
	
	public StanzaBuia(String nome) {
		this(nome, ATTREZZO_ILLUMINANTE_DEFAULT);
	}
	
	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.nomeAttrezzoIlluminante = nomeAttrezzo;
	}
	
	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(this.nomeAttrezzoIlluminante)) {
			return super.getDescrizione();
		}
		else {
			return "qui c'è un buio pesto";
		}
	}
	
}
