package idc;

import java.util.*;
import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.math.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher.*;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;


public class Agreement extends Object implements Serializable {
	
	private PublicKey RSAPub;
	private Channel chan;

	public Agreement(){
		
	}
	
	public void integrity(){
		assert(RSAPub!=null);
		assert(chan!=null);
	}
	
}
