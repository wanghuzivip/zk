/*package com.cy.ssm.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.cy.ssm.constants.ErrorCode;


@Controller 
public class RuleController2 {
	
	@RequestMapping(value="/addRule", method=RequestMethod.POST)
	public @ResponseBody String addRule(@RequestParam MultipartFile[] myfiles, HttpServletRequest request) throws IOException{
		JSONObject result = new JSONObject();
		String version = request.getParameter("version");
		if(version ==null || "".equals(version)){
			result.put("status",ErrorCode.PARAMETER_ERROR);
			result.put("status", ErrorCode.PARAMETER_ERROR_DESC);
			return result.toJSONString();
		}
		
		//���ֻ���ϴ�һ���ļ�����ֻ��ҪMultipartFile���ͽ����ļ����ɣ�����������ʽָ��@RequestParamע��
		//������ϴ�����ļ�����ô �����Ҫ��MultipartFile[]�����������ļ������һ�Ҫָ��@RequestParamע��
		//�����ϴ�����ļ�ʱ��ǰ̨���е�����<input type="file"/>��name��Ӧ����myfiles������������myfiles�޷���ȡ�������ϴ����ļ�
		for(MultipartFile myfile : myfiles){
			if(myfile.isEmpty()){
				System.out.println("�ļ�δ�ϴ�");
			}else{
				System.out.println("�ļ�����: " + myfile.getSize());
				System.out.println("�ļ�����: " + myfile.getContentType());
				System.out.println("�ļ�����: " + myfile.getName());
				System.out.println("�ļ�ԭ��: " + myfile.getOriginalFilename());
				System.out.println("========================================");
				//����õ���Tomcat�����������ļ����ϴ���\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\�ļ�����
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				//���ﲻ�ش���IO���رյ����⣬��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص������ǿ�����Դ���֪����
				FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));
			}
		}
		
		return "../../index";
	}
	
	
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String addUser(@RequestParam MultipartFile[] myfiles, HttpServletRequest request) throws IOException{
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		System.out.println("name:"+username);
		System.out.println("nickname:"+nickname);
		System.out.println("password:"+password);
		System.out.println("email:"+email);
		//���ֻ���ϴ�һ���ļ�����ֻ��ҪMultipartFile���ͽ����ļ����ɣ�����������ʽָ��@RequestParamע��
		//������ϴ�����ļ�����ô �����Ҫ��MultipartFile[]�����������ļ������һ�Ҫָ��@RequestParamע��
		//�����ϴ�����ļ�ʱ��ǰ̨���е�����<input type="file"/>��name��Ӧ����myfiles������������myfiles�޷���ȡ�������ϴ����ļ�
		for(MultipartFile myfile : myfiles){
			if(myfile.isEmpty()){
				System.out.println("�ļ�δ�ϴ�");
			}else{
				System.out.println("�ļ�����: " + myfile.getSize());
				System.out.println("�ļ�����: " + myfile.getContentType());
				System.out.println("�ļ�����: " + myfile.getName());
				System.out.println("�ļ�ԭ��: " + myfile.getOriginalFilename());
				System.out.println("========================================");
				//����õ���Tomcat�����������ļ����ϴ���\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\�ļ�����
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				//���ﲻ�ش���IO���رյ����⣬��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص������ǿ�����Դ���֪����
				FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));
			}
		}
		
		return "../../index";
	}
	

}
*/