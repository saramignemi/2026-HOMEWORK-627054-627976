package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
/**
 * Una semplice classe che rappresenta il labirinto della partita.
 * Contiene stanze collegate tra loro, e mantiene accessibile 
 * la stanza iniziale, vincente e quella corrente in qualunque momento.
 * 
 * @author Sara Mignemi
 * @author Juan L. Perez G.
 * @see Stanza
 * @see Attrezzo
 * @see it.uniroma3.diadia.Partita Partita
 * @version base
 */
public class Labirinto {
	private Stanza stanzaIniziale; // Nel caso dovesse servire sapere a che stanza abbiamo inizato
	private Stanza stanzaVincente; 
	private Stanza stanzaCorrente; 
	
	/**
	 * Crea un labirinto, per ora il labirinto viene generato fisso, quindi il costruttore è senza parametri
	 */
	public Labirinto() {
		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo pc = new Attrezzo("pc",2);
		Attrezzo passepartout = new Attrezzo("passepartout", 1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new StanzaBloccata("Atrio", "nord");
		Stanza aulaN11 = new StanzaBuia("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		laboratorio.addAttrezzo(pc);
		aulaN11.addAttrezzo(passepartout);

		// il gioco comincia nell'atrio
		this.stanzaIniziale = atrio; 
        this.stanzaCorrente = atrio;  
		this.stanzaVincente = biblioteca;	
	}
	
	/**
	 * Imposta la stanza corrente del labirinto
	 * @param stanzaCorrente la stanza da impostare come corrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente; 
	}
	
	/**
	 * Ritorna la stanza corrente nel labirinto.
	 * @return La stanza corrente.
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Ritorna la stanza vincente del labirinto.
	 * @return La stanza vincente.
	 */
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	
	/**
	 * Ritorna la stanza iniziale del labirinto
	 * @return La stanza iniziale.
	 */
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale; 
	}
}
