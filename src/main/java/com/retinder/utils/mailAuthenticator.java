package com.retinder.utils;

import java.net.Authenticator;
import java.net.PasswordAuthentication;



public class mailAuthenticator extends Authenticator {
	
	String userName;
	String userPwd;
	public mailAuthenticator(String userName,String userPwd){
		this.userName=userName;
		this.userPwd=userPwd;
	}
	
	protected PasswordAuthentication getpasswordauthen() {
		return new PasswordAuthentication(userName, userPwd.toCharArray());
	}

}
