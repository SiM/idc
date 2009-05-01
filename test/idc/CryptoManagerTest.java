/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idc;

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
public class CryptoManagerTest {

    public CryptoManagerTest() {
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
    * Test of SignMessage method, of class CryptoManager.
    */
   @Test
   public void SignMessage() {
      System.out.println("SignMessage");
      Message msg = null;
      CryptoManager instance = new CryptoManager();
      instance.SignMessage(msg);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of decode method, of class CryptoManager.
    */
   @Test
   public void decode() {
      System.out.println("decode");
      int id_chan = 0;
      Message msg = null;
      CryptoManager instance = new CryptoManager();
      Message expResult = null;

      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

}