import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import java.io.*;


//Todo: 
//Fix ParserConfig class to take x number of search keywords.
//Perhaps accummulate results and print out in the end, instead
//of when they are found.

class JSONParserExample {

	public static void main (String [] args) {
		
		ParserConfig myConfig = new ParserConfig(args);
		
		myConfig.setJsonFilename("fixed_json_twitter.json");
		myConfig.setSuccessMessage("-- Match(es) found!");
		
		myConfig.setFieldNameToSearch("twitterBio");
		myConfig.setFieldValueToSearch("model");
		myConfig.setFieldNameToReturn("twitterDisplayName");
				
		ObjectMapper mapper = new ObjectMapper();
		
		//System.out.println("Point ONE");
		
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
			
			System.out.println("Point TWO");
			
			while (jp.nextToken() == JsonToken.START_OBJECT)
			{	
				System.out.println("Point THREE");
				//Read as an object -
				//but we would need to use reflection to pull out variable fieldnames
				//twitterUser = null;
				try 
				{
					twitterUser = mapper.readValue(jp, TwitterUser.class);
					System.out.println("Point FOUR");
					
					String twitterBio 			= twitterUser.getTwitterBio();
					String twitterDisplayName 	= twitterUser.getTwitterDisplayName();
					String searchCriteria		= myConfig.getFieldValueToSearch().toLowerCase();
					
					//System.out.println(twitterBio);	
					if (twitterBio.contains(searchCriteria)) {
						outputString += "TwitterDisplayName: " + twitterDisplayName + "\n";
						matchCounter++;
					}										
				} catch (Exception e) 
				{
					System.out.println("Inner exception caught: " + e);
					jp.nextToken();
				}

			}//End while
			
			//If any matches were found, show success message and then show match(es)
			if (matchCounter > 0) 
			{
				System.out.println(myConfig.getSuccessMessage());
				System.out.println(outputString);
			}
			System.out.println("Total number of matching records: " + matchCounter);
			
			jp.close();
				
		} catch (Exception e) {
			System.out.print("here-->  ");
			System.out.println(e);
		}//End try
		
	}
	
	
}//End class
