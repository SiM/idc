/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ihm;

import idc.Channel;
import idc.FriendNode;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author fridim
 */
public class ListData extends AbstractListModel {

   private List dataList;

   public ListData(List data) {
      assert (data != null);
      dataList = data;
   }

   public int getSize() {
      return dataList.size();
   }

   public Object getElementAt(int index) {
      if (dataList.get(index).getClass().toString().equals("class idc.FriendNode") || dataList.get(index).getClass().toString().equals("class idc.Node")) {
         return ((FriendNode) dataList.get(index)).getNickname();
      } else if (dataList.get(index).getClass().toString().equals("class idc.Channel")) {
         return ((Channel) dataList.get(index)).getName();
      }
      return null;
   }

   public Object getRawElement(int index) {
      return dataList.get(index);
   }

   public void refreshList() {
      fireContentsChanged(this, 0, this.getSize());
      
   }
}