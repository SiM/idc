package idc;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.security.*;

/**
 *
 * @author fridim
 */
public class Main {

   public static IDCManager manager;

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      CryptoManager CM = new CryptoManager();
      manager = new IDCManager();
   }
}
