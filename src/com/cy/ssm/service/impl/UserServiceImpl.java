package com.cy.ssm.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.ssm.beans.User;
import com.cy.ssm.mapper.UserMapper;
import com.cy.ssm.service.IUserService;
@Service
public class UserServiceImpl implements IUserService{
	
	@Resource
	private UserMapper userMapper;

	@Override
	public String addRegistCode(User user) {
		try {
			String registCode = UUID.randomUUID().toString();
			registCode = registCode.replace("-", "");
			user.setRegistCode(registCode);
			if(userMapper.addRegistCode(user)>=1){
				return registCode;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}


	@Override
	public List<User> findRegistCode(User user) {
		List<User> users = null;
		try {
			users = userMapper.findRegistCode(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return users;
	}


	@Override
	public int updateRegistCode(User user) {
		int flag = 0;
		try {
			flag = userMapper.updateRegistCode(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		return flag;
	}


	@Override
	public int deleteRegistCode(long id) {
		int flag = 0;
		try {
			flag = userMapper.deleteRegistCode(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		return flag;
	}


	public UserMapper getUserMapper() {
		return userMapper;
	}


	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

}
