package org.rotlogix.servlets;

public class User {
	
	
	private String myage;
	private String mysex;
	private String mylocation;
	private transient Boolean isAdmin = false;

	public User(String age, String sex, String location){
		myage = age;
		mysex = sex;
		mylocation = location;
		
	}
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public Boolean setIsAdmin() {
		return isAdmin;
	}

	public String getMyage() {
		return myage;
	}


	public String getMysex() {
		return mysex;
	}


	public String getMylocation() {
		return mylocation;
	}


	public String profile() {
		return "Profile:" + " " + myage + " " + mysex + " " + mylocation;
		
	}
	
}
