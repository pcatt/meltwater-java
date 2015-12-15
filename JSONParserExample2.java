import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import java.io.*;


//Todo: 
//Fix ParserConfig class to take x number of search keywords.
//Perhaps accummulate results and print out in the end, instead
//of when they are found.

///Updated while statements, but still trying to use dirty Twitter JSON

class JSONParserExample2 {

	public static void main (String [] args) {
		
		ParserConfig myConfig = new ParserConfig(args);
		
		myConfig.setJsonFilename("fixed_json_twitter.json");
		myConfig.setSuccessMessage("-- Match(es) found!");
		
		myConfig.setFieldNameToSearch("twitterBio");
		myConfig.setFieldValueToSearch("model");
		myConfig.setFieldNameToReturn("twitterDisplayName");
				
		ObjectMapper mapper = new ObjectMapper();
		
		//System.out.println("Point ONE");
		
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

			
			System.out.println("Point TWO");
			
			System.out.println("Token is: " + jp.nextToken());
			
			while (jp.nextToken() != JsonToken.END_ARRAY) {
				System.out.println("NOT END OF ARRAY");
				while (jp.nextToken() != JsonToken.END_OBJECT)
				{	
					objectCounter++;
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
						System.out.println("Inner exception caught--> " + e);
						System.out.println("Object number: " + objectCounter);
						//System.out.println("Token is: " + jp.getText());
					}

				}//End while
			}
			
			//If any matches were found, show success message and then show match(es)
			if (matchCounter > 0) 
			{
				System.out.println(myConfig.getSuccessMessage());
				System.out.println(outputString);
			}
			System.out.println("Total number of matching records: " + matchCounter);
			
			jp.close();
				
		} catch (Exception f) {
			System.out.println("Object number: " + objectCounter);
			System.out.print("Outer excepton caught-->  ");
			System.out.println(f);
		}//End try
		
	}
	
	
}//End class
