package com.cy.ssm.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cy.ssm.beans.UserBean;
import com.cy.ssm.service.IUserService;

@Controller
public class UserController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private IUserService userServiceImpl;
	
	@RequestMapping("/addRegistCode")
	public  @ResponseBody String addRegistCode(HttpServletRequest req){
		log.info("---------------this is test-------------------");
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("desc", "ok");
		return object.toJSONString();
	}
	
	@RequestMapping("/findRegistCode")
	public  @ResponseBody String findRegistCode(HttpServletRequest req,UserBean user){
		log.info(user);
		
		
		return "{a:1,b:2}";
	}
	@RequestMapping("/updateRegistCode")
	public  @ResponseBody String updateRegistCode(HttpServletRequest req,UserBean user){
		log.info(user);
		
		
		return "{a:1,b:2}";
	}
	@RequestMapping("/deleteRegistCode")
	public  @ResponseBody String deleteRegistCode(HttpServletRequest req,UserBean user){
		log.info(user);
		
		
		return "{a:1,b:2}";
	}
	
}
