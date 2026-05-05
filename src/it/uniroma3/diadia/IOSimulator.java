package it.uniroma3.diadia;

public class IOSimulator implements IO{
	private String lastString;
	private String[] inputs;
	private int index;
	
	public IOSimulator(String... inputs) {
		this.inputs = inputs;
		this.index = 0;
	}
	
	public String leggiRiga() {
		return this.inputs[this.index++];
	}
	public void mostraMessaggio(String messaggio) {
		this.lastString = messaggio;
	}
	
	public String getLastString() {
		return this.lastString;
	}
}
