package com.cy.ssm.controller;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.ssm.beans.Audio;
import com.cy.ssm.constants.ErrorCode;
import com.cy.ssm.service.IAudioService;
import com.cy.ssm.util.Tool; 


@Controller 
public class AudioController {
	private Logger log = Logger.getLogger(this.getClass());
	private String audioDir = Tool.getValue("audio.dir");
	@Resource
	private IAudioService audioServiceImpl;
		
	@RequestMapping(value="/addAudio", method=RequestMethod.POST)
	public @ResponseBody String addOrUpdateaudio(HttpServletRequest request,long userId,String audioText) {
		log.info("---------------addAudio start-------------------");
		JSONObject result = new JSONObject();
		if(userId <= 0 || audioText == null){
			result.put("status",ErrorCode.PARAMETER_ERROR);
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
	                    String id =  UUID.randomUUID().toString().replace("-", "");
	                    //创建目录  基本+用户id+版本+加id
	                    String dir = audioDir+File.separator+userId+File.separator+id;
	                    FileUtils.forceMkdir(new File(dir));
	                    //上传
	                    String path=dir+File.separator+file.getOriginalFilename();
	                    file.transferTo(new File(path));
	                    Audio audio = new Audio();
	                    audio.setAudioUrl(path);
	                    audio.setId(id);
	                    audio.setUserId(userId);
	                    audio.setName(file.getOriginalFilename());
	                    audio.setAudioText(audioText);
	                    int flag = audioServiceImpl.addAudio(audio);
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
	        log.info("---------------addAudio end-------------------");
	        long  endTime=System.currentTimeMillis();
	        System.out.println("上传文件时间："+String.valueOf(endTime-startTime)+"ms");
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
	
	@RequestMapping(value="/findaudiosByUserId")
	public @ResponseBody String findaudiosByUserId(HttpServletRequest request,long userId) {
		log.info("---------------findaudiosByUserId start-------------------");
		JSONObject result = new JSONObject();
		try {
			List<Audio> audios = audioServiceImpl.findAudioByUserId(userId);
			if( audios != null && !audios.isEmpty()){
				log.info("---------------findaudiosByUserId end-------------------");
				JSONArray array = (JSONArray) JSON.toJSON(audios);
				result.put("code", ErrorCode.OK);
				result.put("desc", ErrorCode.OK_DESC);
				result.put("data",array.toJSONString() );
				return result.toJSONString();
			}else{
				log.info("---------------findaudiosByUserId end-------------------");
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
	
    @RequestMapping("/downloadaudio/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) throws IOException {
	    try {
	    	if(id ==null || "".equals(id)){
	    		return new ResponseEntity<byte[]>(null,null, HttpStatus.NOT_FOUND);
	    	}
	    	Audio audio = audioServiceImpl.findAudioById(id);
	    	 if(audio != null){
	    		 HttpHeaders headers = new HttpHeaders();
	    	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    	        headers.setContentDispositionFormData("attachment", audio.getName());
	    	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(audio.getAudioUrl())),
	    	                                          headers, HttpStatus.OK);
	    	 }else{
	    		 return new ResponseEntity<byte[]>(null,null, HttpStatus.NOT_FOUND);
	    	 }
		} catch (Exception e) {
			log.error("服务端出错："+e.getMessage());
			return new ResponseEntity<byte[]>(null,null, HttpStatus.NOT_FOUND);
		}
    }
}
