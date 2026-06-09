package it.uniroma3.diadia.comandi;

import java.io.File;
import java.util.Scanner;
import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	static {
	    try {
	        String packageName = "it.uniroma3.diadia.comandi";
	        String path = packageName.replace('.', '/');
	        File dir = new File(Thread.currentThread()
	            .getContextClassLoader()
	            .getResource(path).toURI());
	        
	        for (File file : dir.listFiles()) {
	            String fileName = file.getName();
	            if (fileName.startsWith("Comando") && fileName.endsWith(".class")) {
	                Class<?> classe = Class.forName(packageName + "." + fileName.replace(".class", ""));
	                // salta interfacce e classi astratte
	                if (!classe.isInterface() 
	                	    && !java.lang.reflect.Modifier.isAbstract(classe.getModifiers())
	                	    && !classe.getSimpleName().equals("ComandoNonValido")) {
	                	    classe.getDeclaredConstructor().newInstance();
	                	}
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public Comando costruisciComando(String istruzione, IO io) {
	    Scanner scannerDiParole = new Scanner(istruzione == null ? "" : istruzione);
	    String nomeComando = null;
	    String parametro = null;
	    if (scannerDiParole.hasNext())
	        nomeComando = scannerDiParole.next();
	    if (scannerDiParole.hasNext())
	        parametro = scannerDiParole.next();
	    
	    Comando comando = null;
	    try {
	        StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
	        nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
	        nomeClasse.append(nomeComando.substring(1));
	        comando = (Comando) Class.forName(nomeClasse.toString())
	                                 .getDeclaredConstructor()
	                                 .newInstance();
	    } catch (Exception e) {
	        comando = new ComandoNonValido();
	    }
	    comando.setParametro(parametro);
	    comando.setIO(io);
	    return comando;
	}
}