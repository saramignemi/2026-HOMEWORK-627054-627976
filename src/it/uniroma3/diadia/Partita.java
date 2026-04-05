package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private Giocatore giocatore;
	private Labirinto labirinto; 
	private boolean finita;
	
	/**
	 * Crea una nuova partita, inizializzando il {@link Giocatore giocatore},
	 * il {@link Labirinto labirinto} e impostando la partita come non finita
	 */
	public Partita(){
		this.giocatore = new Giocatore(); 
		this.labirinto = new Labirinto(); 
		this.finita = false;
	}
	
	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	/**
	 * Imposta la stanza corrente del labirinto
	 * @param stanzaCorrente La {@link Stanza stanza} da impostare come corrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.labirinto.setStanzaCorrente(stanzaCorrente); 
	}
	
	/**
	 * Imposta il numero di CFU del giocatore
	 * @param cfu Il numero di CFU da impostare
	 */
	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);		
	}
	
	/**
	 * Ritorna il riferimento al {@link Labirinto labirinto} della partita
	 * @return Il labirinto della partita
	 */
	public Labirinto getLabirinto() {
		return this.labirinto; 
	}
	
	/**
	 * Ritorna il riferimento alla {@link Stanza stanza} corrente del labirinto
	 * @return La stanza in cui si trova attualmente il giocatore
	 */
	public Stanza getStanzaCorrente() {
		return this.labirinto.getStanzaCorrente(); 
	}
	
	/**
	 * Ritorna il riferimento alla {@link Stanza stanza} vincente del labirinto
	 * @return La stanza che determina la vittoria del giocatore
	 */
	public Stanza getStanzaVincente() {
		return this.labirinto.getStanzaVincente(); 
	}
	
	/**
	 * Ritorna il numero di CFU del giocatore
	 * @return Il numero di CFU attuali
	 */
	public int getCfu() {
		return this.giocatore.getCfu(); 
	}
	
	/**
	 * Ritorna il riferimento al {@link Giocatore giocatore} della partita
	 * @return Il giocatore della partita
	 */
	public Giocatore getGiocatore() {
		return this.giocatore; 
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.getCfu() == 0);
	}
	
}
