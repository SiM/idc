/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idc;

import java.io.Serializable;

/**
 *
 * @author fridim
 */
public class QuitNotice implements Serializable {
   private byte[] id;
   
   QuitNotice() {
      id = IDCManager.myNode.getId();
   }
}
