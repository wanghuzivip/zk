package com.cy.ssm.controller;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cy.ssm.beans.Rule;
import com.cy.ssm.constants.ErrorCode;
import com.cy.ssm.service.IRuleService;
import com.cy.ssm.util.Tool; 


@Controller 
public class RuleController {
	private Logger log = Logger.getLogger(this.getClass());
	private String ruleDir = Tool.getValue("rule.dir");
	@Resource
	private IRuleService ruleServiceImpl;
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
	
	@RequestMapping(value="/addOrUpdateRule", method=RequestMethod.POST)
	public @ResponseBody String addOrUpdateRule(HttpServletRequest request,String version) {
		JSONObject result = new JSONObject();
		if(version ==null || "".equals(version)){
			result.put("code",ErrorCode.PARAMETER_ERROR);
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
	            Iterator<String> iter=multiRequest.getFileNames();
	            while(iter.hasNext()){
	                //һ�α��������ļ�
	                MultipartFile file=multiRequest.getFile(iter.next().toString());
	                if(file!=null){
	                	System.out.println(file.getOriginalFilename());
	                    String path=ruleDir+File.separator+file.getOriginalFilename();
	                    //�ϴ�
	                    file.transferTo(new File(path));
	                    Rule rule = ruleServiceImpl.findRule();
	                    int flag = 0;
	                    if(rule != null){
	                    	rule.setRuleDbUrl(path);
		                    rule.setRuleDbVersion(version);
		                    flag = ruleServiceImpl.updateRule(rule);
	                    }else{
	                    	rule = new Rule();
		                    rule.setRuleDbUrl(path);
		                    rule.setRuleDbVersion(version);
		                    flag = ruleServiceImpl.addRule(rule);
	                    }
	                    if(flag > 0){
	                    	result.put("code", ErrorCode.OK);
	            			result.put("desc", ErrorCode.OK_DESC);
	            			return result.toJSONString();
	                    }else{
	                    	result.put("code", ErrorCode.ADDORUPDATE_ERROR);
	            			result.put("desc", ErrorCode.ADDORUPDATE_ERROR_DESC);
	            			return result.toJSONString();
	                    }
	                }
	            }
	        }else{
	        	result.put("code", ErrorCode.PARAMETER_ERROR);
				result.put("desc", ErrorCode.PARAMETER_ERROR_DESC);
				return result.toJSONString();
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
	
	@RequestMapping(value="/findRule")
	public @ResponseBody String findRule(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
            Rule rule = ruleServiceImpl.findRule();
            if(rule != null){
            	result.put("code", ErrorCode.OK);
    			result.put("desc", ErrorCode.OK_DESC);
    			result.put("data", ((JSONObject)JSON.toJSON(rule)).toJSONString());
    			return result.toJSONString();
            }else{
            	result.put("code", ErrorCode.OK);
    			result.put("desc", ErrorCode.OK_DESC);
    			return result.toJSONString();
            }
		} catch (Exception e) {
			e.printStackTrace();
			log.error("addRegistCode error|code:"+ErrorCode.UNKNOW+"|desc:"+ErrorCode.UNKNOW_DESC);
			result.put("code", ErrorCode.UNKNOW);
			result.put("desc", ErrorCode.UNKNOW_DESC);
			log.error("����˳���"+e.getMessage());
			return result.toJSONString();
		}
    }
	
	/*@RequestMapping(value = "/download/{fileName}")
	public void downloadFile(@PathVariable String fileName) throws IOException{
		// ƴ����ʵ·��
		String realPath = getRequest().getServletContext().getRealPath("/")
				+ "/" + fileName + ".xls";
		// ��ȡ�ļ�
		File file = new File(realPath);
		if(file.exists()){
			OutputStream os = new BufferedOutputStream(getResponse().getOutputStream());
			try {
				getResponse().setContentType("application/octet-stream");
				if (getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {   //IE�����
					fileName = URLEncoder.encode(fileName + ".xls", "UTF-8");  
				} else {  
					fileName = URLDecoder.decode(fileName + ".xls");//���������
				}
				
				getResponse().setHeader("Content-disposition", "attachment; filename="
						+ new String(fileName.getBytes("utf-8"), "ISO8859-1")); // ָ�����ص��ļ���
				os.write(FileUtils.readFileToByteArray(file));
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(os != null){
					os.close();
				}
			}
		}
	}*/
/*	
	@RequestMapping("downloadRule")
    public void download(HttpServletResponse res) throws IOException {
        OutputStream os = res.getOutputStream();
        try {
            res.reset();
            res.setHeader("Content-Disposition", "attachment; filename=dict.txt");
            res.setContentType("application/octet-stream; charset=utf-8");
            os.write(FileUtils.readFileToByteArray(getDictionaryFile()));
            os.flush();
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
*/
	
	
	
    @RequestMapping("downloadRule")
    public ResponseEntity<byte[]> download() throws IOException {
	    try {
	    	 Rule rule = ruleServiceImpl.findRule();
	    	 if(rule != null){
	    		 HttpHeaders headers = new HttpHeaders();
	    	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    	        headers.setContentDispositionFormData("attachment", rule.getName());
	    	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(rule.getRuleDbUrl())),
	    	                                          headers, HttpStatus.OK);
	    	 }else{
	    		 return new ResponseEntity<byte[]>(null,null, HttpStatus.NOT_FOUND);
	    	 }
		} catch (Exception e) {
			return new ResponseEntity<byte[]>(null,null, HttpStatus.NOT_FOUND);
		}
    }

	public static void main(String[] args) {
		System.out.println(HttpStatus.OK);
	}
}
