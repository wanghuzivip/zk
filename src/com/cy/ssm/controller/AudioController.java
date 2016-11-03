package com.cy.ssm.controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import org.springframework.util.MultiValueMap;
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
import com.cy.ssm.view.AudioData;
import com.cy.ssm.view.AudioItemView;
import com.cy.ssm.view.AudioView; 


@Controller 
public class AudioController {
	private Logger log = Logger.getLogger(this.getClass());
	private String audioDir = Tool.getValue("audio.dir");
	@Resource
	private IAudioService audioServiceImpl;
	@RequestMapping(value="/addAudio", method=RequestMethod.POST)
	public @ResponseBody String addOrUpdateaudio(HttpServletRequest request,String data,long userId,int version) {
		log.info("---------------addAudio start-------------------");
		JSONObject result = new JSONObject();
		if(userId <= 0 || version <= 0 || data == null || "".equals(data)){
			result.put("status",ErrorCode.PARAMETER_ERROR);
			result.put("status", ErrorCode.PARAMETER_ERROR_DESC);
			return result.toJSONString();
		}
		JSONObject audioJosn = null;
		try {
			audioJosn = JSON.parseObject(data);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code",ErrorCode.PARAMETER_PARSE_ERROR);
			result.put("status", ErrorCode.PARAMETER_PARSE_ERROR_DESC);
			log.error("参数解析json对象的时候出错");
			return result.toJSONString();
		}
		try {
			//判断版本信息
			List<Audio> audios = audioServiceImpl.findAudioByUserId(userId);
			if(audios != null && !audios.isEmpty()){
				if(version < audios.get(0).getVersion()){
					result.put("status",ErrorCode.VERSION_SMALL_ERROR);
					result.put("status", ErrorCode.VERSION_SMALL_ERROR_DESC);
					return result.toJSONString();
				}
			}
			long  startTime=System.currentTimeMillis();
	         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
	        //检查form中是否有enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request)){
	            //将request变成多部分request
	            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
	            //获取所有文件
	            MultiValueMap<String,MultipartFile> map =  multiRequest.getMultiFileMap();
	            
	            String audioSrc = audioDir+File.separator+"audio"+File.separator+userId;
	            String textSrc = audioDir+File.separator+"text"+File.separator+userId;
                FileUtils.forceMkdir(new File(audioSrc));
                FileUtils.forceMkdir(new File(textSrc));
                
	            Set<String> keys = audioJosn.keySet();
	            if(keys != null && !keys.isEmpty()){
	            	/* FileUtils.cleanDirectory(new File(audioSrc));
	                 FileUtils.cleanDirectory(new File(textSrc));
	                 audioServiceImpl.deleteAudioByUserId(userId);*/
	            	MultipartFile file = null;
	            	for(String key : keys){
	            		if(map.containsKey(key)){
	            			String id =  UUID.randomUUID().toString().replace("-", "");
		                    String audioDir = audioSrc+File.separator+id;
		                    String audioTextName = null;
		                    String audioTextUrl = null;
		                    String audioTextPath = null;
		                    FileUtils.forceMkdir(new File(audioDir));
		                    file = map.get(key).get(0);
		                    //上传
		                    String audioPath=audioDir+File.separator+file.getOriginalFilename();
		                    
		                    file.transferTo(new File(audioPath));
		                    String value = audioJosn.getString(key);
		                    Audio audio = new Audio();
		                    audio.setAudioUrl(Tool.getDownLoadUrl()+"/downloadaudio/1/"+id);
		                    audio.setId(id);
		                    audio.setUserId(userId);
		                    audio.setAudioName(file.getOriginalFilename());
		                    audio.setAudioPath(audioPath);
		                    audio.setVersion(version);
		                    if(value!=null && !"".equalsIgnoreCase(value)){
		                    	if(map.containsKey(value)){
				                    String textDir = textSrc+File.separator+id;
				                    audioTextUrl = Tool.getDownLoadUrl()+"/downloadaudio/2/"+id;
				                    FileUtils.forceMkdir(new File(textDir));
				                    MultipartFile file2 = map.get(value).get(0);
				                    audioTextName =  file2.getOriginalFilename();
				                    //上传
				                    audioTextPath=textDir+File.separator+file2.getOriginalFilename();
				                    file2.transferTo(new File(audioTextPath));
		                    	}
		                    }
		                    if(audioTextName!=null&&!"".equals(audioTextName)){
		                    	 audio.setAudioTextName(audioTextName);
		                    }
		                    if(audioTextUrl!=null&&!"".equals(audioTextUrl)){
		                    	  audio.setAudioTextUrl(audioTextUrl);
		                    }
		                    if(audioTextPath!=null&&!"".equals(audioTextPath)){
		                    	audio.setAudioTextPath(audioTextPath);
		                    }
		                    int flag = audioServiceImpl.addAudio(audio);
		                    if(flag <= 0){
		                    	result.put("code", ErrorCode.ADDORUPDATE_ERROR);
		            			result.put("desc", ErrorCode.ADDORUPDATE_ERROR_DESC);
		            			return result.toJSONString();
		                    }
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
		AudioData audioData = new AudioData();
		try {
			List<Audio> audios = audioServiceImpl.findAudioByUserId(userId);
			if( audios != null && !audios.isEmpty()){
				AudioView audioView = new AudioView();
				audioView.setUserId(audios.get(0).getUserId());
				audioView.setVersion(audios.get(0).getVersion());
				List<AudioItemView> audioItemViews = new ArrayList<AudioItemView>();
				AudioItemView audioItemView = null;
				for(Audio audio : audios){
					audioItemView = new AudioItemView();
					audioItemView.setAudioName(audio.getAudioName());
					audioItemView.setAudioTextName(audio.getAudioTextName());
					audioItemView.setAudioTextUrl(audio.getAudioTextUrl());
					audioItemView.setAudioUrl(audio.getAudioUrl());
					audioItemView.setId(audio.getId());
					audioItemViews.add(audioItemView);
				}
				audioView.setAudios(audioItemViews);
				
				log.info("---------------findaudiosByUserId end-------------------");
				audioData.setCode(ErrorCode.OK);
				audioData.setDesc(ErrorCode.OK_DESC);
				audioData.setData(audioView);
				return ((JSONObject)JSON.toJSON(audioData)).toJSONString();
			}else{
				log.info("---------------findaudiosByUserId end-------------------");
				audioData.setCode(ErrorCode.OK);
				audioData.setDesc(ErrorCode.OK_DESC);
				return ((JSONObject)JSON.toJSON(audioData)).toJSONString();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("addRegistCode error|code:"+ErrorCode.UNKNOW+"|desc:"+ErrorCode.UNKNOW_DESC);
			audioData.setCode(ErrorCode.UNKNOW);
			audioData.setDesc(ErrorCode.UNKNOW_DESC);
			log.error("服务端出错："+e.getMessage());
			return ((JSONObject)JSON.toJSON(audioData)).toJSONString();
		}
		
    }
	

	@RequestMapping(value="/delaudiosByIds")
	public @ResponseBody String delaudiosByIds(HttpServletRequest request,String ids) {
		log.info("---------------delaudiosByIds start-------------------");
		JSONObject result = new JSONObject();
		if(ids == null || "".equals(ids)){
			result.put("code", ErrorCode.PARAMETER_ERROR);
			result.put("desc", ErrorCode.PARAMETER_ERROR_DESC);
			return result.toJSONString();
		}
		try {
			String[] idStrs = ids.split(",");
			for(String s : idStrs){
				Audio audio = audioServiceImpl.findAudioById(s);
				if(audio != null){
					
					File file = new File(audio.getAudioPath());
					if(file !=null && file.isFile()){
						file.delete();
					}
					File file1 = new File(audio.getAudioTextPath());
					if(file1 !=null && file1.isFile()){
						file1.delete();
					}
					int flag = audioServiceImpl.deleteAudioById(s);
					log.info("deleteAudioById-----id:"+s+"结果："+flag);
				}
			}
			log.info("---------------delaudiosByIds end-------------------");
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
	
	
    @RequestMapping("/downloadaudio/{type}/{id}")
    public ResponseEntity<byte[]> download(@PathVariable int type,@PathVariable String id) throws IOException {
	    try {
	    	if(id ==null || "".equals(id)|| type <= 0){
	    		return new ResponseEntity<byte[]>(null,null, HttpStatus.NOT_FOUND);
	    	}
	    	Audio audio = audioServiceImpl.findAudioById(id);
	    	 if(audio != null){
	    		 String name = "";
	    		 String path = "";
	    		 if(type == 1){
	    			 name = audio.getAudioName();
		    		 path = audio.getAudioPath(); 
	    		 }else if(type == 2){
	    			 name = audio.getAudioTextName();
		    		 path = audio.getAudioTextPath(); 
	    		 }else{
	    			 return new ResponseEntity<byte[]>(null,null, HttpStatus.NOT_FOUND);
	    		 }
	    		 HttpHeaders headers = new HttpHeaders();
	    	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    	        headers.setContentDispositionFormData("attachment", name);
	    	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(path)),
	    	                                          headers, HttpStatus.OK);
	    	 }else{
	    		 return new ResponseEntity<byte[]>(null,null, HttpStatus.NOT_FOUND);
	    	 }
		} catch (Exception e) {
			log.error("服务端出错："+e.getMessage());
			return new ResponseEntity<byte[]>(null,null, HttpStatus.NOT_FOUND);
		}
    }
    
    
    
    public static void main(String[] args) {
		/*String ss = {aaa:sss;sssddd;};*/
	}
}
