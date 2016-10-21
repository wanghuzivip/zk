package com.cy.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.cy.ssm.constants.ErrorCode;


@Controller 
public class RuleController {
	private Logger log = Logger.getLogger(this.getClass());
	/*
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
	*/
	
	
	@RequestMapping(value="/addRule", method=RequestMethod.POST)
	public @ResponseBody String addRule(HttpServletRequest request,String version) {
		JSONObject result = new JSONObject();
		if(version ==null || "".equals(version)){
			result.put("status",ErrorCode.PARAMETER_ERROR);
			result.put("status", ErrorCode.PARAMETER_ERROR_DESC);
			return result.toJSONString();
		}
		try {
			long  startTime=System.currentTimeMillis();
	         //����ǰ�����ĳ�ʼ����  CommonsMutipartResolver ���ಿ�ֽ�������
	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
	        //���form���Ƿ���enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request)){
	            //��request��ɶಿ��request
	            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
	           //��ȡmultiRequest �����е��ļ���
	            Iterator iter=multiRequest.getFileNames();
	            while(iter.hasNext()){
	                //һ�α��������ļ�
	                MultipartFile file=multiRequest.getFile(iter.next().toString());
	                if(file!=null){
	                	System.out.println(file.getOriginalFilename());
	                    String path="E:/upload/"+file.getOriginalFilename();
	                    //�ϴ�
	                    file.transferTo(new File(path));
	                }
	            }
	        }
	        long  endTime=System.currentTimeMillis();
	        System.out.println("������������ʱ�䣺"+String.valueOf(endTime-startTime)+"ms");
	        result.put("code", ErrorCode.OK);
			result.put("desc", ErrorCode.OK_DESC);
			return result.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("addRegistCode error|code:"+ErrorCode.UNKNOW+"|desc:"+ErrorCode.UNKNOW_DESC);
			result.put("code", ErrorCode.UNKNOW);
			result.put("desc", ErrorCode.UNKNOW_DESC);
			log.error("����˳���"+e.getMessage());
			return result.toJSONString();
			
		}
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
