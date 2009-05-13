/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import idc.*;
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
public class ChannelTest {

	public ChannelTest() {
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
	 * Test of getId method, of class Channel.
	 */
	@Test
	public void getId() {
		System.out.println("getId");
		Channel instance = null;
		int expResult = 0;
		byte[] result = instance.getId();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getName method, of class Channel.
	 */
	@Test
	public void getName() {
		System.out.println("getName");
		Channel instance = null;
		String expResult = "";
		String result = instance.getName();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addNode method, of class Channel.
	 */
	@Test
	public void addNode() {
		System.out.println("addNode");
		Node node = null;
		Channel instance = null;
		instance.addNode(node);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getNodeList method, of class Channel.
	 */
	@Test
	public void getNodeList() {
		System.out.println("getNodeList");
		Channel instance = null;
		List expResult = null;
		List result = instance.getNodeList();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of integrity method, of class Channel.
	 */
	@Test
	public void integrity() {
		System.out.println("integrity");
		Channel instance = null;
		instance.integrity();
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of CreateSecretKey method, of class Channel.
	 */
	@Test
	public void CreateSecretKey() {
		System.out.println("CreateSecretKey");
		Channel instance = null;
		instance.CreateSecretKey();
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of cipher method, of class Channel.
	 */
	@Test
	public void cipher() {
		System.out.println("cipher");
		Message message = null;
		Channel instance = null;
		instance.cipher(message);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of decipher method, of class Channel.
	 */
	@Test
	public void decipher() {
		System.out.println("decipher");
		Message message = null;
		Channel instance = null;
		instance.decipher(message);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

}