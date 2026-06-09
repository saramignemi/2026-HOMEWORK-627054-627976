package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatoreAttrezziNome implements Comparator<Attrezzo>{

	@Override
	public int compare(Attrezzo arg0, Attrezzo arg1) {
		return arg0.getNome().compareTo(arg1.getNome());
	}

}
