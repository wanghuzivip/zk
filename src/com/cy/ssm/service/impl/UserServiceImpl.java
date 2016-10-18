package com.cy.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.ssm.beans.User;
import com.cy.ssm.mapper.UserMapper;
import com.cy.ssm.service.IUserService;
@Service
public class UserServiceImpl implements IUserService{
	
	@Resource
	private UserMapper um;

	@Override
	public String addRegistCode(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> findRegistCode(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int updateRegistCode(User user) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int deleteRegistCode(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
