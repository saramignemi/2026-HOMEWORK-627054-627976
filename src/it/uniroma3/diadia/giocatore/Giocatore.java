package it.uniroma3.diadia.giocatore;

/**
 * Una semplice classe per rappresentare un giocatore all'interno della partita, 
 * contiene la sua borsa e i suoi cfu (energia per muoversi)
 * 
 * @author Sara Mignemi
 * @author Juan L. Perez G.
 * 
 * @see it.uniroma3.diadia.Partita Partita
 * @see Borsa
 * 
 * @version base
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private int cfu; 
	private Borsa borsa; 
	
	/**
	 * Costruisce il giocatore, impostando i cfu iniziali al valore standard 
	 * e la borsa al peso standard
	 */
	public Giocatore() {
		this(CFU_INIZIALI); 	
	}
	
	/**
	 * Costruisce il giocatore, impostando i cfu iniziali al valore passato come parametro 
	 * e la borsa al peso standard
	 * @param cfu Il valore di cfu iniziali da impostare
	 */
	public Giocatore(int cfu) {
		this.cfu = cfu; 
		this.borsa = new Borsa(); 
	}
	
	/**
	 * Costruisce il giocatore, impostando i cfu iniziali al valore passato come parametro 
	 * e la borsa al peso passato anch'esso come parametro
	 * @param cfu Il valore di cfu iniziali da impostare
	 * @param pesoMax Il valore del peso iniziale da impostare
	 */
	public Giocatore(int cfu, int pesoMax) {
		this.cfu = cfu; 
		this.borsa = new Borsa(pesoMax); 
	}
	
	
	/**
	 * Imposta i cfu del giocatore al valore passaato
	 * @param cfu Il valore da impostare dei cfu
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu; 
	}
	
	
	/**
	 * Ritorna i cfu attuali del giocatore
	 * @return La quantità di cfu
	 */
	public int getCfu() {
		return this.cfu; 
	}
	
	/**
	 * Ritorna il riferimento alla borsa del giocatore
	 * @return Il riferimento alla borsa
	 */
	public Borsa getBorsa() {
		return this.borsa; 
	}
}
