package com.cy.ssm.controller;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
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
import com.cy.ssm.beans.BackPic;
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
			log.error("��������json�����ʱ�����");
			return result.toJSONString();
		}
		try {
			//�жϰ汾��Ϣ
			List<Audio> audios = audioServiceImpl.findAudioByUserId(userId);
			if(audios != null && !audios.isEmpty()){
				if(version < audios.get(0).getVersion()){
					result.put("status",ErrorCode.VERSION_SMALL_ERROR);
					result.put("status", ErrorCode.VERSION_SMALL_ERROR_DESC);
					return result.toJSONString();
				}
			}
			long  startTime=System.currentTimeMillis();
	         //����ǰ�����ĳ�ʼ����  CommonsMutipartResolver ���ಿ�ֽ�������
	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
	        //���form���Ƿ���enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request)){
	            //��request��ɶಿ��request
	            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
	            //��ȡ�����ļ�
	            MultiValueMap<String,MultipartFile> map =  multiRequest.getMultiFileMap();
	            
	            String audioSrc = audioDir+File.separator+"audio"+File.separator+userId;
	            String textSrc = audioDir+File.separator+"audio"+File.separator+userId;
                FileUtils.forceMkdir(new File(audioSrc));
                FileUtils.forceMkdir(new File(textSrc));
                
	            Set<String> keys = audioJosn.keySet();
	            if(keys != null && !keys.isEmpty()){
	            	 FileUtils.cleanDirectory(new File(audioSrc));
	                 FileUtils.cleanDirectory(new File(textSrc));
	                 audioServiceImpl.deleteAudioByUserId(userId);
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
		                    //�ϴ�
		                    String audioPath=audioDir+File.separator+file.getOriginalFilename();
		                    
		                    file.transferTo(new File(audioPath));
		                    String value = audioJosn.getString(key);
		                    if(value!=null && !"".equalsIgnoreCase(value)){
		                    	if(map.containsKey(value)){
				                    String textDir = textSrc+File.separator+id;
				                    audioTextName =  file.getOriginalFilename();
				                    audioTextUrl = Tool.getDownLoadUrl()+"/downloadaudio/2/"+id;
				                    FileUtils.forceMkdir(new File(textDir));
				                    file = map.get(value).get(0);
				                    //�ϴ�
				                    audioTextPath=textDir+File.separator+file.getOriginalFilename();
				                    file.transferTo(new File(audioTextPath));
		                    	}
		                    }
		                    Audio audio = new Audio();
		                    audio.setAudioUrl(Tool.getDownLoadUrl()+"/downloadaudio/1/"+id);
		                    audio.setId(id);
		                    audio.setUserId(userId);
		                    audio.setAudioName(file.getOriginalFilename());
		                    audio.setAudioPath(audioPath);
		                    audio.setVersion(version);
		                    
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
	        System.out.println("�ϴ��ļ�ʱ�䣺"+String.valueOf(endTime-startTime)+"ms");
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
			log.error("����˳���"+e.getMessage());
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
			log.error("����˳���"+e.getMessage());
			return new ResponseEntity<byte[]>(null,null, HttpStatus.NOT_FOUND);
		}
    }
    
    
    
    public static void main(String[] args) {
		/*String ss = {aaa:sss;sssddd;};*/
	}
}
