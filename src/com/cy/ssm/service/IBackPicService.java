package com.cy.ssm.service;


import java.util.List;

import com.cy.ssm.beans.BackPic;

public interface IBackPicService {

	public int addBackPic(BackPic backPic) throws Exception;
	
	public List<BackPic> findBackPicByUserId(long userId) throws Exception;
	
	public BackPic findBackPicById(String id) throws Exception;
   
	public int updateBackPic(BackPic backPic) throws Exception;
    
	public int deleteBackPicByUserId(long userId) throws Exception;
	
	public int deleteBackPicById(String id) throws Exception;
	
}
