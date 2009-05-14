package idc;

import java.io.*;

public interface Cipherable {

	/**
	 * Les classes implémentant cette interface sont typiquement les messages.
	 * Tous les éléments pouvant être "chiffrable" doivent implémenter cette
	 * interface.
	 */
	public boolean isCiphered();

	public void setSignature(byte[] s);
	
	public void setAsCiphered(boolean bool);
}
