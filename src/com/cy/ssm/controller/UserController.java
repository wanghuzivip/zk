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
public class UserController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private IUserService userServiceImpl;
	
	@RequestMapping("/addRegistCode")
	public  @ResponseBody String addRegistCode(HttpServletRequest req,String user){
		log.info("---------------addRegistCode start-------------------");
		JSONObject result = new JSONObject();
		JSONObject userJosn = null;
		try {
			userJosn = JSON.parseObject(user);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code",ErrorCode.PARAMETER_PARSE_ERROR);
			result.put("status", ErrorCode.PARAMETER_PARSE_ERROR_DESC);
			log.error("参数解析json对象的时候出错");
			return result.toJSONString();
		}
		try {
			if(userJosn == null){
				result.put("code",ErrorCode.PARAMETER_ERROR);
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
			log.error("服务端出错："+e.getMessage());
			return result.toJSONString();
		}
		
		
	}
	
	
	@RequestMapping("/findRegistCode")
	public  @ResponseBody String findRegistCode(HttpServletRequest req,String user){
		log.info("---------------findRegistCode start-------------------");
		JSONObject result = new JSONObject();
		JSONObject userJosn = null;
		try {
			userJosn = JSON.parseObject(user);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code",ErrorCode.PARAMETER_PARSE_ERROR);
			result.put("status", ErrorCode.PARAMETER_PARSE_ERROR_DESC);
			log.error("参数解析json对象的时候出错");
			return result.toJSONString();
			
		}
		try {
			if(userJosn == null){
				result.put("code",ErrorCode.PARAMETER_ERROR);
				result.put("status", ErrorCode.PARAMETER_ERROR_DESC);
				return result.toJSONString();
			}
			User findUser = new User();
			if(userJosn.containsKey("name")){
				findUser.setName(userJosn.getString("name"));
			}
			if(userJosn.containsKey("registCode")){
				findUser.setRegistCode(userJosn.getString("registCode"));
			}
			if(userJosn.containsKey("hasUsed")){
				findUser.setHasUsed(userJosn.getIntValue("hasUsed"));
			}
			if(userJosn.containsKey("limitTime")){
				findUser.setCreateTime(userJosn.getLong("limitTime"));
			}
			if(userJosn.containsKey("createTime")){
				findUser.setCreateTime(userJosn.getLong("createTime"));
			}
			if(userJosn.containsKey("firstUseTime")){
				findUser.setFirstUseTime(userJosn.getLong("firstUseTime"));
			}
			if(userJosn.containsKey("lastSynTime")){
				findUser.setLastSynTime(userJosn.getLong("lastSynTime"));
			}
			if(userJosn.containsKey("lastSetTime")){
				findUser.setLastSetTime(userJosn.getLong("lastSetTime"));
			}
			if(userJosn.containsKey("codeDbName")){
				findUser.setCodeDbName(userJosn.getString("codeDbName"));
			}
			if(userJosn.containsKey("codeDbUrl")){
				findUser.setCodeDbUrl(userJosn.getString("codeDbUrl"));
			}
			
			if(userJosn.containsKey("codeDbVersion")){
				findUser.setCodeDbVersion(userJosn.getIntValue("codeDbVersion"));
			}
			if(userJosn.containsKey("codeDbLastUpdateTime")){
				findUser.setCodeDbLastUpdateTime(userJosn.getLong("codeDbLastUpdateTime"));
			}
			if(userJosn.containsKey("beizhu")){
				findUser.setBeizhu(userJosn.getString("beizhu"));
			}
			if(userJosn.containsKey("start")){
				findUser.setStart(userJosn.getIntValue("start"));
			}
			if(userJosn.containsKey("limit")){
				if(userJosn.containsKey("start")){
					findUser.setLimit(userJosn.getIntValue("limit"));
				}
			}
			List<User> list = userServiceImpl.findRegistCode(findUser);
			if( list != null && !list.isEmpty()){
				log.info("---------------findRegistCode end-------------------");
				JSONArray array = (JSONArray) JSON.toJSON(list);
				result.put("code", ErrorCode.OK);
				result.put("desc", ErrorCode.OK_DESC);
				result.put("data",array.toJSONString() );
				return result.toJSONString();
			}else{
				log.info("---------------findRegistCode end-------------------");
				result.put("code", ErrorCode.OK);
				result.put("desc", ErrorCode.OK_DESC);
				return result.toJSONString();
			}
		} catch (Exception e) {
			log.error("findRegistCode error|code:"+ErrorCode.UNKNOW+"|desc:"+ErrorCode.UNKNOW_DESC);
			result.put("code", ErrorCode.UNKNOW);
			result.put("desc", ErrorCode.UNKNOW_DESC);
			log.error("服务端出错："+e.getMessage());
			return result.toJSONString();
		}
		
	}
	@RequestMapping("/updateRegistCode")
	public  @ResponseBody String updateRegistCode(HttpServletRequest req,String user){
		log.info("---------------updateRegistCode start-------------------");
		JSONObject result = new JSONObject();
		JSONObject userJosn = null;
		try {
			userJosn = JSON.parseObject(user);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code",ErrorCode.PARAMETER_PARSE_ERROR);
			result.put("status", ErrorCode.PARAMETER_PARSE_ERROR_DESC);
			log.error("参数解析json对象的时候出错");
			return result.toJSONString();
		}
		try {
			if(userJosn == null){
				result.put("code",ErrorCode.PARAMETER_ERROR);
				result.put("status", ErrorCode.PARAMETER_ERROR_DESC);
				return result.toJSONString();
			}
			if(!userJosn.containsKey("id")){
				result.put("code",ErrorCode.PARAMETER_NOUSERID_ERROR);
				result.put("status", ErrorCode.PARAMETER_NOUSERID_ERROR_DESC);
				log.error("参数木有传userId");
				return result.toJSONString();
			}
			
			User fUser = new User();
			fUser.setId(userJosn.getLong("id"));
			List<User> users = userServiceImpl.findRegistCode(fUser);
			if(users == null || users.size() < 1){
				result.put("code",ErrorCode.PARAMETER_NOUSER_ERROR);
				result.put("status", ErrorCode.PARAMETER_NOUSER_ERROR_DESC);
				log.error("木有找到用户");
				return result.toJSONString();
			}
			
			fUser = users.get(0);
			if(userJosn.containsKey("name")){
				fUser.setName(userJosn.getString("name"));
			}
			if(userJosn.containsKey("hasUsed")){
				fUser.setHasUsed(userJosn.getIntValue("hasUsed"));
			}
			if(userJosn.containsKey("limitTime")){
				fUser.setCreateTime(userJosn.getLong("limitTime"));
			}
			if(userJosn.containsKey("createTime")){
				fUser.setCreateTime(userJosn.getLong("createTime"));
			}
			if(userJosn.containsKey("firstUseTime")){
				fUser.setFirstUseTime(userJosn.getLong("firstUseTime"));
			}
			if(userJosn.containsKey("lastSynTime")){
				fUser.setLastSynTime(userJosn.getLong("lastSynTime"));
			}
			if(userJosn.containsKey("lastSetTime")){
				fUser.setLastSetTime(userJosn.getLong("lastSetTime"));
			}
			if(userJosn.containsKey("codeDbName")){
				fUser.setCodeDbName(userJosn.getString("codeDbName"));
			}
			if(userJosn.containsKey("codeDbUrl")){
				fUser.setCodeDbUrl(userJosn.getString("codeDbUrl"));
			}
			
			if(userJosn.containsKey("codeDbVersion")){
				fUser.setCodeDbVersion(userJosn.getIntValue("codeDbVersion"));
			}
			if(userJosn.containsKey("codeDbLastUpdateTime")){
				fUser.setCodeDbLastUpdateTime(userJosn.getLong("codeDbLastUpdateTime"));
			}
			if(userJosn.containsKey("beizhu")){
				fUser.setBeizhu(userJosn.getString("beizhu"));
			}
			int flag = userServiceImpl.updateRegistCode(fUser);
			if( flag > 0){
				log.info("---------------updateRegistCode end-------------------");
				result.put("code", ErrorCode.OK);
				result.put("desc", ErrorCode.OK_DESC);
				return result.toJSONString();
			}else{
				result.put("code", ErrorCode.UPDATE_ERROR);
				result.put("desc", ErrorCode.UPDATE_ERROR_DESC);
				log.error("更新失败");
				return result.toJSONString();
			}
		} catch (Exception e) {
			log.error("updateRegistCode error|code:"+ErrorCode.UNKNOW+"|desc:"+ErrorCode.UNKNOW_DESC);
			result.put("code", ErrorCode.UNKNOW);
			result.put("desc", ErrorCode.UNKNOW_DESC);
			log.error("服务端出错："+e.getMessage());
			return result.toJSONString();
		}
	}
	@RequestMapping("/deleteRegistCode")
	public  @ResponseBody String deleteRegistCode(HttpServletRequest req,long id){
		log.info("---------------deleteRegistCode start-------------------");
		JSONObject result = new JSONObject();
		try {
			if(id <=0){
				result.put("code",ErrorCode.PARAMETER_NOREGISTCODE_ERROR);
				result.put("status", ErrorCode.PARAMETER_NOREGISTCODE_ERROR_DESC);
				log.error("参数木有传registCode");
				return result.toJSONString();
			}
			int flag = userServiceImpl.deleteRegistCode(id);
			if( flag > 0){
				log.info("---------------deleteRegistCode end-------------------");
				result.put("code", ErrorCode.OK);
				result.put("desc", ErrorCode.OK_DESC);
				return result.toJSONString();
			}else{
				result.put("code", ErrorCode.UPDATE_ERROR);
				result.put("desc", ErrorCode.UPDATE_ERROR_DESC);
				log.error("删除失败");
				return result.toJSONString();
			}
		} catch (Exception e) {
			log.error("deleteRegistCode error|code:"+ErrorCode.UNKNOW+"|desc:"+ErrorCode.UNKNOW_DESC);
			result.put("code", ErrorCode.UNKNOW);
			result.put("desc", ErrorCode.UNKNOW_DESC);
			log.error("服务端出错："+e.getMessage());
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