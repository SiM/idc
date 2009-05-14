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
public class CipherableTest {

	public CipherableTest() {
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
	 * Test of isCiphered method, of class Cipherable.
	 */
	@Test
	public void isCiphered() {
		System.out.println("isCiphered");
		Cipherable instance = new Cipherable() {

			public boolean isCiphered() {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			public void setAsCiphered(boolean bool) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
			
			public void setSignature(byte[] s) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		};
		boolean expResult = false;
		boolean result = instance.isCiphered();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setSignature method, of class Cipherable.
	 */
	@Test
	public void setSignature() {
		System.out.println("setSignature");
		byte[] s = null;
		Cipherable instance = new Cipherable() {

			public boolean isCiphered() {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			public void setAsCiphered(boolean bool) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
			
			
			public void setSignature(byte[] s) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		};
		instance.setSignature(s);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

}