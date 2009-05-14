/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import idc.*;
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
public class ComTest {

	public ComTest() {
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
	 * Test of send method, of class Com.
	 */
	@Test
	public void send() {
		System.out.println("send");
		Message msg = null;
		Com instance = new Com() {

			public void send(Message msg) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			public void send(int id_chan, Message msg) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		};
		instance.send(msg);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

}