package com.cy.ssm.service;


import java.util.List;

import com.cy.ssm.beans.User;

public interface IUserService {

	public String addRegistCode(User user);
	
    public List<User> findRegistCode(User user);
    
    public int updateRegistCode(User user) ;
    
    public int deleteRegistCode(long id) ;
    
    public List<User> findUsersByName(String name);
	
}
