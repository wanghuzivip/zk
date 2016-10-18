package com.cy.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.ssm.mapper.UserMapper;
import com.cy.ssm.beans.UserBean;
import com.cy.ssm.service.ILoginService;
@Service
public class LoginServiceImpl implements ILoginService{
	
	@Resource
	private UserMapper um;


	@Override
	public UserBean Login(String username, String password) {
		return null;
	}

}
