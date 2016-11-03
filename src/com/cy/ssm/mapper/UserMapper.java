package com.cy.ssm.mapper;

import java.util.List;

import com.cy.ssm.beans.User;


public interface UserMapper {
   
	public int addRegistCode(User user) throws Exception;
    
	public List<User> findRegistCode(User user) throws Exception;
   
	public int updateRegistCode(User user) throws Exception;
    
	public int deleteRegistCode(long id) throws Exception;
	
	public List<User> findUsersByName(String name) throws Exception;
}
