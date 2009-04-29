package idc;

import java.io.*;

public interface Cipherable {
	/**
	 * Les classes implémentant cette interface sont typiquement les messages.
	 * Tout les éléments pouvant être "chiffrable" doivent implémentent cette interface.
	 */
	public boolean isCiphered();
	
	public void authentification();
	

}
