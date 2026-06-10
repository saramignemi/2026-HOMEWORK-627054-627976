package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Proprieta;

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

	private int cfu;
	private Borsa borsa;

	/**
	 * Costruisce il giocatore leggendo cfu iniziali e peso max borsa
	 * dal file diadia.properties
	 */
	public Giocatore() {
		this(
			Proprieta.getInstance().getCfuIniziali(),
			Proprieta.getInstance().getPesoMaxBorsa()
		);
	}

	/**
	 * Costruisce il giocatore con i cfu passati e il peso borsa da properties
	 */
	public Giocatore(int cfu) {
		this(cfu, Proprieta.getInstance().getPesoMaxBorsa());
	}

	/**
	 * Costruisce il giocatore con cfu e peso borsa esplicitamente specificati
	 */
	public Giocatore(int cfu, int pesoMax) {
		this.cfu = cfu;
		this.borsa = new Borsa(pesoMax);
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public int getCfu() {
		return this.cfu;
	}

	public Borsa getBorsa() {
		return this.borsa;
	}
}