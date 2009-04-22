package idc;

public interface Com {

	/**
	 * Interface regroupant les principale fonctions de communications.
	 * INUTILE SI UTILISATION DES JMS (pour mais l'instant ce n'est pas le cas). 
	 */
	
	//envoie du message msg.
	abstract public void send(Message msg);
	abstract  public void send(int id_chan,Message msg);
	
	
}
