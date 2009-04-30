/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import java.io.File;

/**
 * @author fridim
 */
public class Config {

   static public int port = 44441; // listening port for the server
   static public int broadcastPort = 44442;
   static public int AES_size = 128;
   static public int RSA_size = 1024;
   static public long broadcastSleep = 5000;
   static public String nickname = "fridim";
   static public String keyfile = System.getProperty("user.home") + "/.IDC-known_hosts";
   static public File FilePub = new File(System.getProperty("user.home") + "/.idc.id.pub");
   static public File FilePriv = new File(System.getProperty("user.home") + "/.idc.id.private");
   // Here we can imagine parsing a conf file
   public static void loadConfig() {
   }
}
