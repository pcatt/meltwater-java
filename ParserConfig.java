import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class ParserConfig {

	boolean caseSensitive = false;
	boolean verboseOutput = true;

	String successMessage;

	//Search for field with value...
	String fieldNameToSearch;
	String fieldValueToSearch;
	
	//If found, return the value of this fieldname
	String fieldNameToReturn;
	
	ArrayList<String> searchKeywords; 
	
	String jsonFilename;

	public ParserConfig(String [] args) {
		
		searchKeywords = new ArrayList<String>(); 
		
		if (args.length > 0) {
			for (String keyword : args) {
				searchKeywords.add(keyword);
			}
		}//End if
	}//End ParserConfig
	
	
	//Get methods
	public boolean getVerboseOutput() {
		return verboseOutput;
	}
	
	public String getSuccessMessage() {
		return successMessage;
	}
	
	public String getJsonFilename() {
		return jsonFilename;
	}
	
	public String getFieldNameToSearch() {
		return fieldNameToSearch;
	}
	
	public String getFieldValueToSearch() {
		return fieldValueToSearch;
	}	
	
	public String getFieldNameToReturn() {
		return fieldNameToReturn;
	}
	
	public String[] getSearchKeywordsArray() {
		String[] getSearchKeywords = searchKeywords.toArray(new String[searchKeywords.size()]);
		return getSearchKeywords;
	}
	
	public ArrayList<String> getSearchKeywords() {
		ArrayList<String> getSearchKeywords = searchKeywords;
		return getSearchKeywords;
	}		
	
	public String getSearchKeywords(int pos) {
		String searchKeyword = searchKeywords.get(pos);
		return searchKeyword;
	}
	
	
	//Set methods
	public void setCaseSensitive(boolean inputted_caseSensitive) {
		caseSensitive = inputted_caseSensitive;
	}
	
	public void setSuccessMessage(String inputted_successMessage) {
		successMessage = inputted_successMessage;
	}
	
	public void setJsonFilename(String inputted_filename) {
		jsonFilename = inputted_filename;
	}
	
	public void setFieldNameToSearch(String inputted_fieldname) {
		fieldNameToSearch = inputted_fieldname;
	}
	
	public void setFieldValueToSearch(String inputted_fieldname) {
		fieldValueToSearch = inputted_fieldname;
	}	
	
	public void setFieldNameToReturn(String inputted_fieldname) {
		fieldNameToReturn = inputted_fieldname;
	}	
	
	//Add methods (for keywords)
	public void addKeyword(String inputtedKeyword) {
		searchKeywords.add(inputtedKeyword);
	}
	
	
	//Print keywords
	public void printKeywords() {
		System.out.println("Keyword list: ");
		System.out.println("==============");
		for (String keyword : searchKeywords) {
			System.out.println(keyword);
		}
	}

}


