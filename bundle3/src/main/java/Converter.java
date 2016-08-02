package com.opensourcearchitect;

import java.util.*;

public class Converter {
	String parent = "";
	String child = "";
	String trips = "";
	String tripId = "";
	ArrayList <String> list = new ArrayList<String>();
	
	public String convert (ArrayList<ArrayList<String>> listOLists) throws Exception {
	  	trips += "<Trips>\n";
		
		//Find all trip IDs
		for (int counter=1; counter<6; counter++) {
	  		boolean isDistinct = false;
	  		for(int check=1; check<counter; check++){
	  			if(listOLists.get(counter).get(3).equals(listOLists.get(check).get(3))){
	  				isDistinct = true;
                    break;
                }
            }
            if(!isDistinct){
                list.add(listOLists.get(counter).get(3));
	  		}
            
            //Check for required fields
            if(listOLists.get(counter).get(0).equals(""))
            	throw new Exception("ERROR: ID is missing!");
            else if(listOLists.get(counter).get(1).equals(""))
            	throw new Exception("ERROR: First Name is missing!");
            else if(listOLists.get(counter).get(2).equals(""))
            	throw new Exception("ERROR: Last Name is missing!");
            else if(listOLists.get(counter).get(3).equals(""))
            	throw new Exception("ERROR: Trip ID is missing!");
	  	}
	  	
	  	//Add Trips Ids
	  	for(int x=0; x<list.size(); x++) {
	  		tripId = list.get(x);
	  		trips += ("\t<Trip ID='" + tripId + "'>\n");
	  		
	  		//Add parent travelers
	  		for(int i=1; i<listOLists.size(); i++) {  
				if (listOLists.get(i).get(3).equals(tripId)) {	
					if (listOLists.get(i).get(4).equals("")) {
						parent += ("\t\t<Traveler ID='" + listOLists.get(i).get(0) +
									"' FirstName='" + listOLists.get(i).get(1) +
									"' LastName='" + listOLists.get(i).get(2) +	"'>\n");
				
						if (!(listOLists.get(i).get(5).equals("")))
							parent += ("\t\t\t<Comment>" + listOLists.get(i).get(5) + "</Comment>\n");
					
						//Add child travelers
						for(int j=1; j<listOLists.size(); j++) {
							if (listOLists.get(j).get(4).equals(listOLists.get(i).get(0))) {
								child += ("\t\t\t<Traveler ID='" + listOLists.get(j).get(0) +
												"' FirstName='" + listOLists.get(j).get(1) +
												"' LastName='" + listOLists.get(j).get(2) +	"'>\n");
								
								if (!(listOLists.get(j).get(5).equals("")))
									child += ("\t\t\t\t<Comment>" + listOLists.get(j).get(5) + "</Comment>\n");			
					
								child += ("\t\t\t</Traveler>\n");
							}
						}
						parent += child;
						child = "";
						parent += ("\t\t</Traveler>\n");
					}
					else {
						boolean match = false;
						for (int z=1; z<listOLists.size(); z++) {
							if(listOLists.get(i).get(4).equals(listOLists.get(z).get(0))) {
								match = true;
							}
						}
						if(!match)
							throw new Exception("ERROR: Parent does not exist for traveler ID[" + listOLists.get(i).get(0) + "]");
					}
				}
				trips += parent;
				parent = "";
	  		}
	  		trips += "\t</Trip>\n";
		}
		trips += "</Trips>";
		return trips;
	}
}