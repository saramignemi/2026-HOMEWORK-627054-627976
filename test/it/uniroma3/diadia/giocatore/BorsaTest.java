package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.*;

/**
 * Classe di test per {@link Borsa borsa},
 * verifica il corretto funzionamento dell'aggiunta e rimozione
 * di attrezzi e della rappresentazione in stringa
 *
 * @author Sara Mignemi
 * @author Juan L. Perez G.
 * @see Borsa
 * @see Attrezzo
 * @version base
 */
public class BorsaTest {

	private Borsa borsa;

	@Before
	public void setUp() {
		this.borsa = new Borsa(200);
	}

	/**
	 * Verifica che non sia possibile aggiungere un attrezzo
	 * quando la borsa ha già raggiunto il numero massimo di attrezzi
	 */
	@Test
	public void testAddAttrezzoPiena() {
		this.borsa = new Borsa();
		for (int i=0; i<10; i++) {
			Attrezzo nuovo = new Attrezzo("nuovo" + i, 1);
			this.borsa.addAttrezzo(nuovo);
		}
		Attrezzo eccesso = new Attrezzo("eccesso", 8);
		assertFalse(this.borsa.addAttrezzo(eccesso));
	}

	/**
	 * Verifica che la rimozione di un attrezzo da una borsa vuota
	 * ritorni null
	 */
	@Test
	public void testRemoveAttrezzoVuota() {
		assertNull(this.borsa.removeAttrezzo(""));
	}

	/**
	 * Verifica che la rappresentazione in stringa di una borsa vuota
	 * sia "Borsa vuota"
	 */
	@Test
	public void testToStringVuota() {
		assertEquals("Borsa vuota", this.borsa.toString());
	}
	
	@Test
	public void testSortingPeso() {
		Attrezzo peso5 = new Attrezzo("peso 5", 5);
		Attrezzo peso1 = new Attrezzo("peso 1", 1);
		Attrezzo peso3 = new Attrezzo("peso 3", 3);
		this.borsa.addAttrezzo(peso5);
		this.borsa.addAttrezzo(peso1);
		this.borsa.addAttrezzo(peso3);
		
		List<Attrezzo> sortedList = new ArrayList<Attrezzo>();
		sortedList.add(peso1);
		sortedList.add(peso3);
		sortedList.add(peso5);
		
		List<Attrezzo> customList = this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(sortedList, customList);
		
		
	}
	
	@Test
	public void testSortedUnique() {
	    Attrezzo peso5a = new Attrezzo("aaaa", 5);
	    Attrezzo peso5b = new Attrezzo("zzzz", 5);
	    Attrezzo peso1 = new Attrezzo("peso1", 1);
	    this.borsa.addAttrezzo(peso5a);
	    this.borsa.addAttrezzo(peso5b);
	    this.borsa.addAttrezzo(peso1);
	    
	    SortedSet<Attrezzo> risultato = this.borsa.getSortedSetOrdinatoPerPeso();
	   
	    assertEquals(3, risultato.size()); // entrambi i peso5 sono presenti
	    assertEquals("peso1", risultato.first().getNome()); // primo è il più leggero
	    assertEquals("zzzz", risultato.last().getNome()); // ultimo è peso5b per nome
		
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_ordineCorretto() {
	    this.borsa.addAttrezzo(new Attrezzo("b", 3));
	    this.borsa.addAttrezzo(new Attrezzo("a", 1));
	    this.borsa.addAttrezzo(new Attrezzo("c", 2));
	    List<Attrezzo> risultato = this.borsa.getContenutoOrdinatoPerPeso();
	    assertEquals("a", risultato.get(0).getNome());
	    assertEquals("c", risultato.get(1).getNome());
	    assertEquals("b", risultato.get(2).getNome());
	}

	@Test
	public void testGetContenutoOrdinatoPerPeso_paritaDiPesoOrdinaPerNome() {
	    this.borsa.addAttrezzo(new Attrezzo("z", 5));
	    this.borsa.addAttrezzo(new Attrezzo("a", 5));
	    List<Attrezzo> risultato = this.borsa.getContenutoOrdinatoPerPeso();
	    assertEquals("a", risultato.get(0).getNome());
	    assertEquals("z", risultato.get(1).getNome());
	}

	@Test
	public void testGetContenutoOrdinatoPerNome_ordineAlfabetico() {
	    this.borsa.addAttrezzo(new Attrezzo("z", 1));
	    this.borsa.addAttrezzo(new Attrezzo("a", 2));
	    this.borsa.addAttrezzo(new Attrezzo("m", 3));
	    SortedSet<Attrezzo> risultato = this.borsa.getContenutoOrdinatoPerNome();
	    assertEquals("a", risultato.first().getNome());
	    assertEquals("z", risultato.last().getNome());
	}

	@Test
	public void testGetContenutoRaggruppatoPerPeso_gruppazioneCorrtta() {
	    this.borsa.addAttrezzo(new Attrezzo("a", 5));
	    this.borsa.addAttrezzo(new Attrezzo("b", 5));
	    this.borsa.addAttrezzo(new Attrezzo("c", 1));
	    Map<Integer, Set<Attrezzo>> risultato = this.borsa.getContenutoRaggruppatoPerPeso();
	    assertEquals(2, risultato.size());
	    assertEquals(2, risultato.get(5).size());
	    assertEquals(1, risultato.get(1).size());
	}

	@Test
	public void testGetContenutoRaggruppatoPerPeso_nessunGruppoVuoto() {
	    this.borsa.addAttrezzo(new Attrezzo("a", 1));
	    this.borsa.addAttrezzo(new Attrezzo("b", 2));
	    Map<Integer, Set<Attrezzo>> risultato = this.borsa.getContenutoRaggruppatoPerPeso();
	    for (Set<Attrezzo> gruppo : risultato.values())
	        assertFalse(gruppo.isEmpty());
	}

	@Test
	public void testSortedSetOrdinatoPerPeso_stessoPesoNomeDiversoRimangonDistinti() {
	    this.borsa.addAttrezzo(new Attrezzo("aaaa", 5));
	    this.borsa.addAttrezzo(new Attrezzo("zzzz", 5));
	    this.borsa.addAttrezzo(new Attrezzo("peso1", 1));
	    SortedSet<Attrezzo> risultato = this.borsa.getSortedSetOrdinatoPerPeso();
	    assertEquals(3, risultato.size());
	    assertEquals("peso1", risultato.first().getNome());
	    assertEquals("zzzz", risultato.last().getNome());
	}
}