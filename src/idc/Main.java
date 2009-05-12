/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import ihm.*;

import java.security.*;
import java.io.IOException;
import java.net.*;
import java.security.*;

/**
 * 
 * @author fridim
 */
public class Main {

   /**
    * @param args
    *            the command line arguments
    */
   public static void main(String[] args) {
      Accueil temp = new Accueil(/*manager,crypto*/);

      temp.setVisible(true);

      Runtime.getRuntime().addShutdownHook(new Thread() {
         // This method is called during shutdown
         public void run() {
         // Do shutdown work ...
            IDCManager.send(new QuitNotice());
         }
      });
   }
}


