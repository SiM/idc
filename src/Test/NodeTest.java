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
public class NodeTest {

	public NodeTest() {
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
	 * Test of getNickname method, of class Node.
	 */
	@Test
	public void getNickname() {
		System.out.println("getNickname");
		Node instance = null;
		String expResult = "";
		String result = instance.getNickname();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getId method, of class Node.
	 */
	@Test
	public void getId() {
		System.out.println("getId");
		Node instance = null;
		String expResult = "";
		//String result = instance.getId();
		//assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of checkKey method, of class Node.
	 */
	@Test
	public void checkKey() {
		System.out.println("checkKey");
		Node instance = null;
		boolean expResult = false;
		//boolean result = instance.checkKey();
		//assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of send method, of class Node.
	 */
	@Test
	public void send() {
		System.out.println("send");
		Message message = null;
		Node instance = null;
		instance.send(message);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of integrity method, of class Node.
	 */
	@Test
	public void integrity() {
		System.out.println("integrity");
		Node instance = null;
		//instance.integrity();
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

}