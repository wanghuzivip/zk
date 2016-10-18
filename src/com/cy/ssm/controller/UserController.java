package com.cy.ssm.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.ssm.beans.User;
import com.cy.ssm.beans.UserBean;
import com.cy.ssm.service.ILoginService;

@Controller
public class UserController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private ILoginService loginServiceImpl;
	
	@RequestMapping("/addRegistCode")
	public  @ResponseBody String addRegistCode(HttpServletRequest req,UserBean user){
		log.info(user);
		
		
		return "{a:1,b:2}";
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
