package idc;

public interface Com {

   /**
    * Interface regroupant les principale fonctions de communications. 
    */
   //envoie du message msg.
   abstract public void send(Message msg);

   abstract public void send(int id_chan, Message msg);
}
