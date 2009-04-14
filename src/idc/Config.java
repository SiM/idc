/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idc;

/**
 *
 * @author fridim
 */
public class Config {
    static public int port = 44441; // listening port for the server
    static public int broadcastPort = 44442; // listening port for the server
    static public String keyfile = System.getProperty("user.home") + "/.IDC-known_hosts";
    
    // Here we can imagine parsing a conf file
}
