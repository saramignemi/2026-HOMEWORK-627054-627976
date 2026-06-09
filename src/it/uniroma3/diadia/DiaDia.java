package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.personaggi.*;


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
		IO io = new IOConsole(); 
		Labirinto labirinto = new LabirintoBuilder()

			    // stanze
			    .addStanzaBloccata("Atrio", "nord")          // StanzaBloccata
			    .addStanzaBuia("Aula N11")                   // StanzaBuia
			    .addStanza("Aula N10")                       // Stanza normale
			    .addStanza("Laboratorio Campus")
			    .addStanza("Biblioteca")

			    // iniziale e vincente
			    .addStanzaIniziale("Atrio")
			    .addStanzaVincente("Biblioteca")

			    // adiacenze (uguali all’originale)
			    .addAdiacenza("Atrio", "Biblioteca", "nord")
			    .addAdiacenza("Atrio", "Aula N11", "est")
			    .addAdiacenza("Atrio", "Aula N10", "sud")
			    .addAdiacenza("Atrio", "Laboratorio Campus", "ovest")

			    .addAdiacenza("Aula N11", "Laboratorio Campus", "est")
			    .addAdiacenza("Aula N11", "Atrio", "ovest")

			    .addAdiacenza("Aula N10", "Atrio", "nord")
			    .addAdiacenza("Aula N10", "Aula N11", "est")
			    .addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")

			    .addAdiacenza("Laboratorio Campus", "Atrio", "est")
			    .addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")

			    .addAdiacenza("Biblioteca", "Atrio", "sud")

			    // attrezzi
			    .inStanza("Aula N10")
			    .addAttrezzo("lanterna", 3)

			    .inStanza("Atrio")
			    .addAttrezzo("osso", 1)

			    .inStanza("Laboratorio Campus")
			    .addAttrezzo("pc", 2)

			    .inStanza("Aula N11")
			    .addAttrezzo("passepartout", 1)

			    // personaggi
			    .inStanza("Atrio")
			    .addCane("Buddy")
			    
			    .inStanza("Laboratorio Campus")
			    .addMago("Saruman")
			    
			    .inStanza("Aula N10")
			    .addStrega("Kureha")
			    // build finale
			    .getLabirinto();
		
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
	}
}