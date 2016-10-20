package com.cy.ssm.mapper;

import java.util.List;

import com.cy.ssm.beans.BackPic;


public interface BackPicMapper {
   
	public int addBackPic(BackPic backPic) throws Exception;
    
	public List<BackPic> findBackPicByUserId(long userId) throws Exception;
	
	public BackPic findBackPicById(long id) throws Exception;
	
	public int updateBackPic(BackPic backPic) throws Exception;
    
	public int deleteBackPicByUserId(long userId) throws Exception;
	
	public int deleteBackPicById(long id) throws Exception;
	
}
