//Simple JSON file parser using Jackson
//Written by Phil Cattani
//December 12, 2015
//Sample JSON records generated using http://www.json-generator.com/

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import java.io.*;


//Todo: 
//Fix ParserConfig class to take x number of search keywords.

///Using first While Statements (keep this!), using clean JSON with 60 records.  Update to use two search criteria

class JSONParserExample3 {

	public static void main (String [] args) {
		
		ParserConfig myConfig = new ParserConfig(args);
		
		myConfig.setJsonFilename("users_sample.json");
		myConfig.setSuccessMessage("-- Match(es) found!");
		
		myConfig.setFieldNameToSearch("about");
		myConfig.setFieldValueToSearch("officia");
		myConfig.setFieldNameToReturn("lname");
				
		ObjectMapper mapper = new ObjectMapper();
				
		int objectCounter = 0;
		
		try 
		{
			//Crete JsonFactory
			JsonFactory jsonFactory = new JsonFactory();
			JsonParser jp = jsonFactory.createJsonParser(new File(myConfig.getJsonFilename()));
		  
			//Open array (JSON file containing many entries starts with '[')
			jp.nextToken();
		  			
			User user;
			String outputString = "";
			int matchCounter = 0;
			
			while (jp.nextToken() == JsonToken.START_OBJECT)
			{	
				objectCounter++;
				
				//Read as an object -
				//but we would need to use reflection to pull out variable fieldnames
				//twitterUser = null;
				user = mapper.readValue(jp, User.class);
				
				String userAbout 			= user.getAbout();
				String userLname 			= user.getLName();
				String userFname			= user.getFName();
				String userFullname			= userFname + " " + userLname;
				
				String searchCriteria		= myConfig.getFieldValueToSearch().toLowerCase();
				
				System.out.println("Processing record#: " + objectCounter + "  " + userFullname);
				
				if (userAbout.contains(searchCriteria)) {
					outputString += "userFullname: " + userFullname + "\n";
					matchCounter++;
				}										

			}//End while
				
			
			//If any matches were found, show success message and then show match(es)
			if (matchCounter > 0) 
			{
				System.out.println(myConfig.getSuccessMessage());
				System.out.println(outputString);
			}
			System.out.println("Total number of matching records: " + matchCounter + " out of " + objectCounter + " total.");
			
			jp.close();
				
		} catch (Exception f) {
			System.out.print("Outer excepton caught-->  " + f);
			System.out.println("Object number: " + objectCounter);			
		}//End try
		
	}
	
	
}//End class
