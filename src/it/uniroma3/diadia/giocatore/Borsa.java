package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;


/**
 * Una classe che modella la borsa del giocatore, 
 * contenente attrezzi in quantità e peso limitati.
 * 
 * @author Sara Mignemi
 * @author Juan L. Perez G.
 * @see Attrezzo
 * @see Giocatore
 * @version base
 */
public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	/**
	 * Crea una borsa con peso massimo standard
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	/**
	 * Crea una borsa con peso massimo passato come parametro
	 * @param pesoMax Il peso massimo da impostare alla borsa
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // Speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	/**
	 * Aggiunge un {@link Attrezzo attrezzo} alla borsa, controllando se supera i due limiti impostati
	 * @param attrezzo l'attrezzo da aggiungere alla borsa
	 * @return true se riesce ad aggiungere l'attrezzo, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	/**
	 * Ritorna il peso massimo contenibile nella borsa
	 * @return Peso massimo
	 */
	public int getPesoMax() {
		return this.pesoMax;
	}
	
	/**
	 * Ritorna il riferimento a un attrezzo della borsa 
	 * in base al nome, passato come parametro
	 * @param nomeAttrezzo Stringa che contiene il nome dell'attrezzo da cercare
	 * @return Riferimento all'attrezzo trovato, se non esiste nessun attrezzo con tale nome ritorna null
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i=0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = this.attrezzi[i];
		return a;
	}
	
	/**
	 * Ritorna il peso totale della borsa causato dagli oggetti contenuti
	 * @return Il peso totale
	 */
	public int getPeso() {
		int peso = 0;
		for (int i=0; i<this.numeroAttrezzi; i++)
			peso = peso + this.attrezzi[i].getPeso();
		return peso;
	}
		
	/**
	 * Controlla se la borsa è vuota
	 * @return true se la borsa è vuota, false altrimenti
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
		
	/**
	 * Controlla se un attrezzo è contenuto nella borsa
	 * @param nomeAttrezzo il nome dell'attrezzo da cercare nella borsa
	 * @return true se l'attrezzo è presente, false altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * Rimuove un attrezzo dalla borsa, ritornandone il riferimento se è presente
	 * @param nomeAttrezzo il nome dell'attrezzo da rimuovere
	 * @return il riferimento all'attrezzo se è presente, null altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if (this.hasAttrezzo(nomeAttrezzo)) {
			for (int i=0; i<this.numeroAttrezzi; i++) {
				if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
					a = this.attrezzi[i]; 
					for (int j=i; j<this.numeroAttrezzi-1; j++) {
						this.attrezzi[j] = this.attrezzi[j+1]; 
					}
					this.numeroAttrezzi--; 
					this.attrezzi[this.numeroAttrezzi] = null; 
					break; 
				}
			}
		}
		return a;
	}
		
	/**
	 * Trasforma l'intera borsa, compresa del suo contenuto,
	 * in una stringa, e la ritorna
	 * @return La stringa costituita dal contenuto della borsa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}
