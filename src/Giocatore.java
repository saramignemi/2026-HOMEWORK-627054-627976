
public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private int cfu; 
	private Borsa borsa; 
	
	public Giocatore() {
		this(CFU_INIZIALI); 	
	}
	
	public Giocatore(int cfu) {
		this.cfu = cfu; 
		this.borsa = new Borsa(); 
	}
	
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
