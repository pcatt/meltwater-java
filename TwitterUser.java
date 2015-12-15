public class TwitterUser {
	  
	//Private class vars 
	private String id;
	private String url;
	private String content;
	private String authorId;
	private String twitterUsername;
	private String twitterDisplayName;
	private String twitterBio;
	private String tupleType;
	
	//Get methods
	public String getId() 					{ return id; 				}
	public String getUrl() 					{ return url; 				}
	public String getContent() 				{ return content; 			}
	public String getAuthorId() 			{ return authorId; 			}
	public String getTwitterUsername() 		{ return twitterUsername; 	}
	public String getTwitterDisplayName() 	{ return twitterDisplayName;}
	public String getTwitterBio() 			{ return twitterBio;		}
	public String getTupleType() 			{ return tupleType; 		}
	
	//Set methods
	public void setId(String inputted_id) 									{ id 		= inputted_id; 		}
	public void setUrl(String inputted_url) 								{ url 		= inputted_url; 	}
	public void setContent(String inputted_content) 						{ content 	= inputted_content; }
	public void setAuthorId(String inputted_authorId) 						{ authorId 	= inputted_authorId;}
	public void setTwitterUsername(String inputted_twitterUsername)			{ twitterUsername	= inputted_twitterUsername;	}
	public void setTwitterBio(String inputted_twitterBio)					{ twitterBio		= inputted_twitterBio;		}
	public void setTupleType(String inputted_tupleType) 					{ tupleType			= inputted_tupleType;	 	}
	
}
