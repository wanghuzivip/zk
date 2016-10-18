package com.cy.ssm.service;


import com.cy.ssm.beans.UserBean;

public interface ILoginService {

	public UserBean Login(String username,String password);
	
	
}
