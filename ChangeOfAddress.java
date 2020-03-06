package com.filely.pdf.core;

public class ChangeOfAddress {
	
	public static class inUser{
		
		public boolean isUserLoggedIn; 
		public String address;
		
		
		inUser(boolean loggedin){
			isUserLoggedIn = loggedin;
		}
		
		public void setAddress(String addr){
			this.address = addr;
		}
	}
	
	public static class inValidator{
		
		private boolean isValidAddress; 
		
		inValidator(boolean isValidAddress){
			isValidAddress = isValidAddress;
		}
	}
	
	public static class inDialogBox{
		public String message;
		
		inDialogBox(String messg){
			message = messg;
		}
		
		public String getAddress(){
			return message;
		}
	}
	

	
	public inDialogBox changeAddress(inUser user, inValidator valid, inDialogBox indialog){
		String newAddress; 
		
		if(user.isUserLoggedIn){
			newAddress = indialog.getAddress();
			
			if(valid.isValidAddress){
				user.setAddress(newAddress);
			}else{
				indialog.message = "Invalid Address";
			}
		}else{
			indialog.message = "not logged in";
		}
		return indialog;
	}
	

}
