package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	// Spostato in FabbricaDiComandiFisarmonica
	//static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IO io;
	
	public DiaDia(IO io, Labirinto lab) {
		this.partita = new Partita(lab);
		this.io = io;
	}
	
	public void gioca() throws Exception {
		String istruzione; 
		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		this.io.mostraMessaggio("\n"+ this.partita.getStanzaCorrente().getDescrizione());
		do		
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione, this.io);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (this.partita.getCfu() <= 0)
			io.mostraMessaggio("Hai esaurito i CFU...");

		return this.partita.isFinita();
		}
	

	public static void main(String[] argc) throws Exception {
		// Scanner creato una volta sola qui nel main, chiuso automaticamente
		// a fine partita dal try-with-resources — System.in non viene mai
		// chiuso prematuramente da leggiRiga()
		try (Scanner scanner = new Scanner(System.in)) {
			IO io = new IOConsole(scanner);
			try {
				CaricatoreLabirinto caricatore = new CaricatoreLabirinto("default");
				Labirinto labirinto = caricatore.carica();
				DiaDia gioco = new DiaDia(io, labirinto);
				gioco.gioca();
			} catch (FileNotFoundException e) {
				System.err.println("File labirinto non trovato: " + e.getMessage());
			} catch (FormatoFileNonValidoException e) {
				System.err.println("Errore nel file: " + e.getMessage());
			}
		}
	}

}