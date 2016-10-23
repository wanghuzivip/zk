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
		
		//如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
		//如果想上传多个文件，那么 这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
		//并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
		for(MultipartFile myfile : myfiles){
			if(myfile.isEmpty()){
				System.out.println("文件未上传");
			}else{
				System.out.println("文件长度: " + myfile.getSize());
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("文件名称: " + myfile.getName());
				System.out.println("文件原名: " + myfile.getOriginalFilename());
				System.out.println("========================================");
				//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
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
	         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
	        //检查form中是否有enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request)){
	            //将request变成多部分request
	            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
	           //获取multiRequest 中所有的文件名
	            Iterator<String> iter=multiRequest.getFileNames();
	            while(iter.hasNext()){
	                //一次遍历所有文件
	                MultipartFile file=multiRequest.getFile(iter.next().toString());
	                if(file!=null){
	                	System.out.println(file.getOriginalFilename());
	                    String path=ruleDir+File.separator+file.getOriginalFilename();
	                    //上传
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
	        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
	        result.put("code", ErrorCode.OK);
			result.put("desc", ErrorCode.OK_DESC);
			return result.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("addRegistCode error|code:"+ErrorCode.UNKNOW+"|desc:"+ErrorCode.UNKNOW_DESC);
			result.put("code", ErrorCode.UNKNOW);
			result.put("desc", ErrorCode.UNKNOW_DESC);
			log.error("服务端出错："+e.getMessage());
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
			log.error("服务端出错："+e.getMessage());
			return result.toJSONString();
		}
    }
	
	/*@RequestMapping(value = "/download/{fileName}")
	public void downloadFile(@PathVariable String fileName) throws IOException{
		// 拼接真实路径
		String realPath = getRequest().getServletContext().getRealPath("/")
				+ "/" + fileName + ".xls";
		// 读取文件
		File file = new File(realPath);
		if(file.exists()){
			OutputStream os = new BufferedOutputStream(getResponse().getOutputStream());
			try {
				getResponse().setContentType("application/octet-stream");
				if (getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {   //IE浏览器
					fileName = URLEncoder.encode(fileName + ".xls", "UTF-8");  
				} else {  
					fileName = URLDecoder.decode(fileName + ".xls");//其他浏览器
				}
				
				getResponse().setHeader("Content-disposition", "attachment; filename="
						+ new String(fileName.getBytes("utf-8"), "ISO8859-1")); // 指定下载的文件名
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
