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
public class ServerThreadTest {

    public ServerThreadTest() {
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
    * Test of run method, of class ServerThread.
    */
   @Test
   public void run() {
      System.out.println("run");
      ServerThread instance = null;
      instance.run();
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

}