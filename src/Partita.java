



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
	
	public Partita(){
		this.giocatore = new Giocatore(); 
		this.labirinto = new Labirinto(); 
		this.finita = false;
	}
	
	
	public void setFinita() {
		this.finita = true;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.labirinto.setStanzaCorrente(stanzaCorrente); 
	}
	
	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);		
	}
	
	
	public Labirinto getLabirinto() {
		return this.labirinto; 
	}
	
	public Stanza getStanzaCorrente() {
		return this.labirinto.getStanzaCorrente(); 
	}
	
	public Stanza getStanzaVincente() {
		return this.labirinto.getStanzaVincente(); 
	}
	
	public int getCfu() {
		return this.giocatore.getCfu(); 
	}
	
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
