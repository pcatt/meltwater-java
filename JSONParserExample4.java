//Simple JSON file parser using Jackson
//Written by Phil Cattani
//December 12, 2015
//Processing JSON Twitter file...

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import java.io.*;

class JSONParserExample4 {

	public static void main (String [] args) {
		
		//Args can be passed to main method call at command line, and search
		//keywords are added to the config.
		ParserConfig myConfig = new ParserConfig(args);
		
		myConfig.setJsonFilename("fixed_json_twitter.json");
		myConfig.setFieldValueToSearch("Journalist");
		myConfig.setFieldNameToSearch("twitterBio");
		
		//Search keywords
		myConfig.addKeyword("journalist");
		myConfig.addKeyword("volunteer");
		myConfig.addKeyword("chef");
		
		//Set Case Sensitivity
		myConfig.setCaseSensitive(false);
		
		//Set success message
		myConfig.setSuccessMessage("\n -- Match(es) found! \ntwitterUsername(s) where designated fields contain search keywords: " + myConfig.getSearchKeywords().toString());		
				
		//Set return field (not used)
		myConfig.setFieldNameToReturn("twitterUsername");
				
		ObjectMapper mapper = new ObjectMapper();
				
		int objectCounter = 0;
		
		try 
		{
			//Crete JsonFactory
			JsonFactory jsonFactory = new JsonFactory();
			JsonParser jp = jsonFactory.createJsonParser(new File(myConfig.getJsonFilename()));
		  
			//Open array (JSON file containing many entries starts with '[')
			jp.nextToken();
		  			
			TwitterUser twitterUser;
			String outputString = "";
			int matchCounter = 0;
			boolean verboseOutput = myConfig.getVerboseOutput();
			
			while (jp.nextToken() == JsonToken.START_OBJECT)
			{	
				objectCounter++;
				
				//Read as an object -
				//but we would need to use reflection to pull out variable fieldnames
				twitterUser = mapper.readValue(jp, TwitterUser.class);
				
				String twitterUsername		= twitterUser.getTwitterUsername();
				String twitterBio 			= twitterUser.getTwitterBio();
				String twitterContent		= twitterUser.getContent();
								
				if (verboseOutput) 
				{
					System.out.println("Processing record #" + objectCounter + ":   " + twitterUsername);
				}
				
				//Search for each of the keywords in SearchKeywords defined in myConfig
				for (String searchKeyword : myConfig.getSearchKeywords()) {
					if (!myConfig.caseSensitive) 
					{
						twitterBio 		= twitterBio.toLowerCase();
						searchKeyword	= searchKeyword.toLowerCase();
					}	
					if (twitterBio.contains(searchKeyword)) 
					{
						outputString += "Record #: " + objectCounter + ": " + twitterUsername + "\n";
						matchCounter++;
					}	
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
			System.out.print("Excepton caught-->  " + f);
			System.out.println("Object number: " + objectCounter);			
		}//End try
		
	}
	
	
}//End class
