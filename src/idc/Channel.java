package idc;

import idc.FriendNode;
import idc.Node;
import java.util.*;
import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;


public class Channel {
	private long id;
	private String name;
	private List nodes;
	private SecretKey key;//ne doit jamais être accessible de manière direct via des getters ou setters.
	
	
	public Channel(String nickname){
		nodes =new ArrayList();
		key=CryptoManager.CreateSecretKey();
		id=new idGen().getID();
		
	}
	
	public void addNode(Node node){
		nodes.add(node);
		
	}
	
	public void integrity(){
		assert(name!=null);
		assert(name.length()>=0);
		assert(nodes!=null);
		assert(key!=null);
	}
}


final class idGen{
	private long id=0;
	
	public idGen(){
		//nothing to do 
	}
	
	public long getID(){
		id=id+1;
		return id;
	}
	
	public void integrity(){
		assert(id>=0);
	}
}