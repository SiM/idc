package idc;

import idc.FriendNode;
import idc.Node;
import java.util.*;
import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;


public class Channel {
	static private int id;
	static private String name;
	static private List nodes;
		
	static public int getId(){
		integrity();
		return id;
	}
	
	public String getName(){
		integrity();
		return name;
	}
	
	
	
	public Channel(String nickname){
		nodes =new ArrayList();
		id=IdGen.getID();
		integrity();
	}
	

	public void addNode(Node node){
		integrity();
		nodes.add(node);		
	}
	
	static public void integrity(){
		assert(name!=null);
		assert(name.length()>=0);
		assert(nodes!=null);		
	}
}

