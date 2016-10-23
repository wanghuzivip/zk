package com.cy.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.ssm.beans.BackPic;
import com.cy.ssm.mapper.BackPicMapper;
import com.cy.ssm.service.IBackPicService;
@Service
public class BackPicServiceImpl implements IBackPicService{
	
	@Resource
	private BackPicMapper backPicMapper;

	public BackPicMapper getBackPicMapper() {
		return backPicMapper;
	}


	public void setBackPicMapper(BackPicMapper backPicMapper) {
		this.backPicMapper = backPicMapper;
	}


	@Override
	public List<BackPic> findBackPicByUserId(long userId) throws Exception {
		List<BackPic> backPics = null;
		try {
			backPics = backPicMapper.findBackPicByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return backPics;
	}
	@Override
	public BackPic findBackPicById(String id) throws Exception {
		BackPic backPic = null;
		try {
			backPic = backPicMapper.findBackPicById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return backPic;
	}
	@Override
	public int updateBackPic(BackPic backPic) throws Exception {
		int flag = 0;
		try {
			flag = backPicMapper.addBackPic(backPic);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return flag;
	}
	@Override
	public int deleteBackPicByUserId(long userId) throws Exception {
		int flag = 0;
		try {
			flag = backPicMapper.deleteBackPicByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return flag;
	}
	@Override
	public int deleteBackPicById(String id) throws Exception {
		int flag = 0;
		try {
			flag = backPicMapper.deleteBackPicById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return flag;
	}


	@Override
	public int addBackPic(BackPic backPic) throws Exception {
		int flag = 0;
		try {
			flag = backPicMapper.addBackPic(backPic);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return flag;
	}

}
