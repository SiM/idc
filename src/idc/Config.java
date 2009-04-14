/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idc;

import java.io.File;

/**
 *
 * @author fridim
 */
public class Config {
    static public int port = 44441; // listening port for the server
    static public String keyfile = System.getProperty("user.home") + "/.IDC-known_hosts";
    static public File FilePub = new File("id.pub");
    static public File FilePriv = new File("id.private");
    // Here we can imagine parsing a conf file
}
