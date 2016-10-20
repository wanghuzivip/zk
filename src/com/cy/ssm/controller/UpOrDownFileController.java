package com.cy.ssm.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.ssm.beans.User;
import com.cy.ssm.constants.ErrorCode;
import com.cy.ssm.service.IUserService;

@Controller
public class UpOrDownFileController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private IUserService userServiceImpl;
	
	@RequestMapping("/upPic")
	public  @ResponseBody String upPic(HttpServletRequest req,String user){
		log.info("---------------addRegistCode start-------------------");
		JSONObject result = new JSONObject();
		JSONObject userJosn = null;
		try {
			userJosn = JSON.parseObject(user);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status",ErrorCode.PARAMETER_PARSE_ERROR);
			result.put("status", ErrorCode.PARAMETER_PARSE_ERROR_DESC);
			log.error("��������json�����ʱ�����");
			return result.toJSONString();
		}
		try {
			if(userJosn == null){
				result.put("status",ErrorCode.PARAMETER_ERROR);
				result.put("status", ErrorCode.PARAMETER_ERROR_DESC);
				return result.toJSONString();
			}
			User addUser = new User();
			if(userJosn.containsKey("name")){
				addUser.setName(userJosn.getString("name"));
			}
			if(userJosn.containsKey("hasUsed")){
				addUser.setHasUsed(userJosn.getIntValue("hasUsed"));
			}
			if(userJosn.containsKey("limitTime")){
				addUser.setCreateTime(userJosn.getLong("limitTime"));
			}
			if(userJosn.containsKey("createTime")){
				addUser.setCreateTime(userJosn.getLong("createTime"));
			}
			if(userJosn.containsKey("firstUseTime")){
				addUser.setFirstUseTime(userJosn.getLong("firstUseTime"));
			}
			if(userJosn.containsKey("lastSynTime")){
				addUser.setLastSynTime(userJosn.getLong("lastSynTime"));
			}
			if(userJosn.containsKey("lastSetTime")){
				addUser.setLastSetTime(userJosn.getLong("lastSetTime"));
			}
			if(userJosn.containsKey("codeDbName")){
				addUser.setCodeDbName(userJosn.getString("codeDbName"));
			}
			if(userJosn.containsKey("codeDbUrl")){
				addUser.setCodeDbUrl(userJosn.getString("codeDbUrl"));
			}
			
			if(userJosn.containsKey("codeDbVersion")){
				addUser.setCodeDbVersion(userJosn.getIntValue("codeDbVersion"));
			}
			if(userJosn.containsKey("codeDbLastUpdateTime")){
				addUser.setCodeDbLastUpdateTime(userJosn.getLong("codeDbLastUpdateTime"));
			}
			if(userJosn.containsKey("beizhu")){
				addUser.setBeizhu(userJosn.getString("beizhu"));
			}
			String registCode =  userServiceImpl.addRegistCode(addUser);
			if(registCode != null){
				log.info("---------------addRegistCode end-------------------");
				result.put("code", ErrorCode.OK);
				result.put("desc", ErrorCode.OK_DESC);
				result.put("data", registCode);
				return result.toJSONString();
			}else{
				log.error("addRegistCode error|code:"+ErrorCode.UNKNOW+"|desc:"+ErrorCode.UNKNOW_DESC);
				result.put("code", ErrorCode.UNKNOW);
				result.put("desc", ErrorCode.UNKNOW_DESC);
				return result.toJSONString();
			}
		} catch (Exception e) {
			log.error("addRegistCode error|code:"+ErrorCode.UNKNOW+"|desc:"+ErrorCode.UNKNOW_DESC);
			result.put("code", ErrorCode.UNKNOW);
			result.put("desc", ErrorCode.UNKNOW_DESC);
			log.error("����˳�����"+e.getMessage());
			return result.toJSONString();
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		List<User> list = new ArrayList<User>();
		for(int i=0;i<=5;i++){
			User  user = new User();
			user.setBeizhu("22222");
			user.setCodeDbName("fdfg");
			list.add(user);
		}
		
		JSONArray array = (JSONArray) JSON.toJSON(null);
		
		System.out.println(array.toJSONString());
		
	}
	
	
	
}