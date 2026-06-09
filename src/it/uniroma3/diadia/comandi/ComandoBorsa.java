package it.uniroma3.diadia.comandi;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoBorsa extends AbstractComando {

	private String metodo;
	
	@Override
	public String getNome() {
		return "borsa";
	}

	@Override
	public String getParametro() {
		return this.metodo;
	}

	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		StringBuilder out = new StringBuilder();
		if (this.metodo == null)
			io.mostraMessaggio("Devi inserire un metodo tra questi: oPerNome, oPerPeso, gruppoPerPeso");
		else if (this.metodo.equals("oPerNome")) {
			SortedSet<Attrezzo> s = borsa.getContenutoOrdinatoPerNome();
			out.append("{ ");
			for (Attrezzo a : s) {
				out.append(a.getNome() + ", ");
			}
			int index = out.lastIndexOf(",");
			out.deleteCharAt(index);
			out.append("}");
			io.mostraMessaggio(out.toString());
		}
			
		else if (this.metodo.equals("oPerPeso")) {
			List<Attrezzo> s = borsa.getContenutoOrdinatoPerPeso();
			out.append("[ ");
			for (Attrezzo a : s) {
				out.append(a.getNome() + ", ");
			}
			int index = out.lastIndexOf(",");
			out.deleteCharAt(index);
			out.append("]");
			io.mostraMessaggio(out.toString());
		}
		
		else if (this.metodo.equals("gruppoPerPeso")) {
			Map<Integer, Set<Attrezzo>> s = borsa.getContenutoRaggruppatoPerPeso();
			for (Integer i : new TreeSet<Integer>(s.keySet())) {
				out.append("("+i+",{ ");
				for (Attrezzo a : s.get(i)) {
					out.append(a.getNome() + ", ");
				}
				int index = out.lastIndexOf(",");
				out.deleteCharAt(index);
				out.append("} ) ; ");
			}
			io.mostraMessaggio(out.toString());
		}
			
		else io.mostraMessaggio("Metodo " + this.metodo + " inesistente\nDevi inserire un metodo tra questi: oPerNome, oPerPeso, gruppoPerPeso");
		
	}

	@Override
	public void setParametro(String parametro) {
		// Metodo di stampa
		this.metodo = parametro;
	}

}
