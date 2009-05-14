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
public class MessageTest {

	public MessageTest() {
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
	 * Test of isCiphered method, of class Message.
	 */
	@Test
	public void isCiphered() {
		System.out.println("isCiphered");
		Message instance = null;
		boolean expResult = false;
		boolean result = instance.isCiphered();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setAsCiphered method, of class Message.
	 */
	@Test
	public void setAsCiphered() {
		System.out.println("setAsCiphered");
		boolean bool = false;
		Message instance = null;
		instance.setAsCiphered(bool);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getMessage method, of class Message.
	 */
	@Test
	public void getMessage() {
		System.out.println("getMessage");
		Message instance = null;
		String expResult = "";
		String result = instance.getMessage();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getDigest method, of class Message.
	 */
	@Test
	public void getDigest() {
		System.out.println("getDigest");
		Message instance = null;
		byte[] expResult = null;
		byte[] result = instance.getDigest();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of shasum method, of class Message.
	 */
	@Test
	public void shasum() {
		System.out.println("shasum");
		String in = "";
		Message instance = null;
		byte[] expResult = null;
		// byte[] result = instance.shasum(in);
		// assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setSignature method, of class Message.
	 */
	@Test
	public void setSignature() {
		System.out.println("setSignature");
		byte[] s = null;
		Message instance = null;
		instance.setSignature(s);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getData method, of class Message.
	 */
	@Test
	public void getData() {
		System.out.println("getData");
		Message instance = null;
		byte[] expResult = null;
		byte[] result = instance.getData();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setData method, of class Message.
	 */
	@Test
	public void setData() {
		System.out.println("setData");
		byte[] ocTab = null;
		Message instance = null;
		instance.setData(ocTab);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

}