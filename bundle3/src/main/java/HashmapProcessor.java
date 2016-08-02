package com.opensourcearchitect;

import java.util.*;

public class HashmapProcessor {

	// Create a hash map
    HashMap<String,String> hm = new HashMap<String,String>();

	public String store (String tripID) {
	  String value = hm.get(tripID);
	  
	  //check if trip ID doesn't exist
	  if (value == null) {
	  	// Put elements to the map
        hm.put(tripID, tripID);
        
	  	return "SQL: Created Trip ID [" + tripID + "] in table";
	  }
	  else
	  	return "SQL: Trip ID [" + tripID + "] already exists";
	}
}