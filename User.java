public class User {
	  
	//Private class vars 
	private String fname;
	private String lname;
	private String tel;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String about;
	
	//Get methods
	public String getFName() 	{ return fname; 				}
	public String getLName() 	{ return lname; 				}
	public String getFullName() { return fname + " " + lname; 	}
	public String getTel() 		{ return tel; 					}
	public String getAddress() 	{ return address; 				}
	public String getCity() 	{ return city; 					}
	public String getState() 	{ return state;					}
	public String getZip() 		{ return zip; 					}
	public String getAbout() 	{ return about;					}
	
	//Set methods
	public void setFName(String inputted_fname) 		{ fname 	= inputted_fname; 	}
	public void setLName(String inputted_lname) 		{ lname 	= inputted_lname; 	}
	public void setTel(String inputted_tel) 			{ tel 		= inputted_tel; 	}
	public void setAddress(String inputted_address) 	{ address 	= inputted_address;	}
	public void setCity(String inputted_city)			{ city		= inputted_city;	}
	public void setState(String inputted_state)			{ state		= inputted_state;	}
	public void setZip(String inputted_zip) 			{ zip		= inputted_zip;	 	}
	public void setAbout(String inputted_about)			{ about		= inputted_about;	}
	
}
