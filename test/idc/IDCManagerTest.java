/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idc;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fridim
 */
public class IDCManagerTest {

    public IDCManagerTest() {
    }

   @BeforeClass
   public static void setUpClass() throws Exception {
   }

   @AfterClass
   public static void tearDownClass() throws Exception {
   }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

   /**
    * Test of getLANNodes method, of class IDCManager.
    */
   @Test
   public void getLANNodes() {
      System.out.println("getLANNodes");
      IDCManager instance = new IDCManager();
      List expResult = null;
      List result = instance.getLANNodes();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of request method, of class IDCManager.
    */
   @Test
   public void request() {
      System.out.println("request");
      Node node = null;
      IDCManager.request(node);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of integrity method, of class IDCManager.
    */
   @Test
   public void integrity() {
      System.out.println("integrity");
      IDCManager instance = new IDCManager();
      instance.integrity();
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

}