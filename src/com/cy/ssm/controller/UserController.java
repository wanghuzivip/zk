package com.cy.ssm.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.cy.ssm.beans.BackPic;
import com.cy.ssm.beans.Rule;
import com.cy.ssm.beans.User;
import com.cy.ssm.constants.ErrorCode;
import com.cy.ssm.service.IAudioService;
import com.cy.ssm.service.IBackPicService;
import com.cy.ssm.service.IRuleService;
import com.cy.ssm.service.IUserService;
import com.cy.ssm.util.Tool;
import com.cy.ssm.view.AudioItemView;
import com.cy.ssm.view.AudioView;
import com.cy.ssm.view.BackPicItemView;
import com.cy.ssm.view.BackPicView;
import com.cy.ssm.view.Data;
import com.cy.ssm.view.Result;
import com.cy.ssm.view.RuleView;
import com.cy.ssm.view.UserView;

@Controller
public class UserController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private IUserService userServiceImpl;
	@Resource 
	private IAudioService audioServiceImpl;
	@Resource
	private IBackPicService backPicServiceImpl;
	@Resource
	private IRuleService ruleServiceImpl;
	private String codeDir = Tool.getValue("code.dir");
	private String backPicDir = Tool.getValue("backPic.dir");
	private String audioDir = Tool.getValue("audio.dir");
	
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
			result.put("desc", ErrorCode.PARAMETER_PARSE_ERROR_DESC);
			log.error("参数解析json对象的时候出错");
			return result.toJSONString();
		}
		try {
			if(userJosn == null){
				result.put("code",ErrorCode.PARAMETER_ERROR);
				result.put("desc", ErrorCode.PARAMETER_ERROR_DESC);
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
				addUser.setLimitTime(userJosn.getLong("limitTime"));
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
			result.put("desc", ErrorCode.PARAMETER_PARSE_ERROR_DESC);
			log.error("参数解析json对象的时候出错");
			return result.toJSONString();
			
		}
		try {
			if(userJosn == null){
				result.put("code",ErrorCode.PARAMETER_ERROR);
				result.put("desc", ErrorCode.PARAMETER_ERROR_DESC);
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
				findUser.setLimitTime(userJosn.getLong("limitTime"));
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
	
	
	@RequestMapping("/findUsersByName")
	public  @ResponseBody String findUsersByName(HttpServletRequest req,String name){
		log.info("---------------findRegistCode start-------------------");
		JSONObject result = new JSONObject();
		try {
			if(name == null || "".equals(name)){
				result.put("code",ErrorCode.PARAMETER_ERROR);
				result.put("desc", ErrorCode.PARAMETER_ERROR_DESC);
				return result.toJSONString();
			}
			List<User> list = userServiceImpl.findUsersByName(name);
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
			result.put("desc", ErrorCode.PARAMETER_PARSE_ERROR_DESC);
			log.error("参数解析json对象的时候出错");
			return result.toJSONString();
		}
		try {
			if(userJosn == null){
				result.put("code",ErrorCode.PARAMETER_ERROR);
				result.put("desc", ErrorCode.PARAMETER_ERROR_DESC);
				return result.toJSONString();
			}
			if(!userJosn.containsKey("id")){
				result.put("code",ErrorCode.PARAMETER_NOUSERID_ERROR);
				result.put("desc", ErrorCode.PARAMETER_NOUSERID_ERROR_DESC);
				log.error("参数木有传userId");
				return result.toJSONString();
			}
			
			User fUser = new User();
			fUser.setId(userJosn.getLong("id"));
			List<User> users = userServiceImpl.findRegistCode(fUser);
			if(users == null || users.size() < 1){
				result.put("code",ErrorCode.PARAMETER_NOUSER_ERROR);
				result.put("desc", ErrorCode.PARAMETER_NOUSER_ERROR_DESC);
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
				fUser.setLimitTime(userJosn.getLong("limitTime"));
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
				result.put("desc", ErrorCode.PARAMETER_NOREGISTCODE_ERROR_DESC);
				log.error("参数木有传registCode");
				return result.toJSONString();
			}
			int flag = userServiceImpl.deleteRegistCode(id);
			if( flag > 0){
				String codeSrc=codeDir+File.separator+id;
				String dirSrc = backPicDir+File.separator+id;
				String audioSrc = audioDir+File.separator+"audio"+File.separator+id;
	            String textSrc = audioDir+File.separator+"text"+File.separator+id;
	            if(new File(codeSrc).exists()){
	            	FileUtils.cleanDirectory(new File(codeSrc));	
	            }
	            if(new File(dirSrc).exists()){
		            FileUtils.cleanDirectory(new File(dirSrc));
	            }
	            if(new File(audioSrc).exists()){
		            FileUtils.cleanDirectory(new File(audioSrc));
	            }
	            if(new File(textSrc).exists()){
		            FileUtils.cleanDirectory(new File(textSrc));
	            }
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
			e.printStackTrace();
			log.error("deleteRegistCode error|code:"+ErrorCode.UNKNOW+"|desc:"+ErrorCode.UNKNOW_DESC);
			result.put("code", ErrorCode.UNKNOW);
			result.put("desc", ErrorCode.UNKNOW_DESC);
			log.error("服务端出错："+e.getMessage());
			return result.toJSONString();
		}
	}
	
	
	@RequestMapping("/getAllData")
	public  @ResponseBody String getAllData(HttpServletRequest req,long userId){
		log.info("---------------getAllData start-------------------");
		
		Result result = new Result();
		result.setCode(0);
		result.setDesc("xxx");
		Data data = new Data();
		try {
			if(userId <=0){
				result.setCode(ErrorCode.PARAMETER_NOREGISTCODE_ERROR);
				result.setDesc(ErrorCode.PARAMETER_NOREGISTCODE_ERROR_DESC);
				log.error("参数木有传registCode");
				return  ((JSONObject)JSON.toJSON(result)).toJSONString();
			}
			//获得用户信息
			User user = new User();
			user.setId(userId);
			List<User> users = userServiceImpl.findRegistCode(user);
			if(users != null && !users.isEmpty()){
				UserView userView = new UserView();
				userView.setBeizhu(users.get(0).getBeizhu());
				userView.setCodeDbLastUpdateTime(users.get(0).getCodeDbLastUpdateTime());
				userView.setCodeDbName(users.get(0).getCodeDbName());
				userView.setCodeDbUrl(users.get(0).getCodeDbUrl());
				userView.setCodeDbVersion(users.get(0).getCodeDbVersion());
				userView.setCreateTime(users.get(0).getFirstUseTime());
				userView.setFirstUseTime(users.get(0).getFirstUseTime());
				userView.setHasUsed(users.get(0).getHasUsed());
				userView.setId(users.get(0).getId());
				userView.setLastSetTime(users.get(0).getLastSetTime());
				userView.setLastSynTime(users.get(0).getLastSynTime());
				userView.setLimitTime(users.get(0).getLimitTime());
				userView.setName(users.get(0).getName());
				userView.setRegistCode(users.get(0).getRegistCode());
				data.setUser(userView);
			}
			//rule
			Rule rule = ruleServiceImpl.findRule();
			if(rule != null){
				RuleView ruleView = new RuleView();
				ruleView.setName(rule.getName());
				ruleView.setRuleDbUrl(rule.getRuleDbUrl());
				ruleView.setRuleDbVersion(rule.getRuleDbVersion());
				ruleView.setUpdateTime(rule.getUpdateTime());
				data.setRule(ruleView);
			}
			//BackPic
			List<BackPic> backPics = backPicServiceImpl.findBackPicByUserId(userId);
			if(backPics != null && !backPics.isEmpty()){
				BackPicView backPicView = new BackPicView();
				backPicView.setUserId(backPics.get(0).getUserId());
				backPicView.setVersion(backPics.get(0).getVersion());
				List<BackPicItemView> backPicViews = new ArrayList<BackPicItemView>();
				for(BackPic backPic : backPics){
					BackPicItemView backPicItemView = new BackPicItemView();
					backPicItemView.setName(backPic.getName());
					backPicItemView.setUrl(backPic.getUrl());
					backPicViews.add(backPicItemView);
				}
				backPicView.setBackPics(backPicViews);
				data.setBackPic(backPicView);
			}
			//Audio
			List<Audio> audios = audioServiceImpl.findAudioByUserId(userId);
			if(audios != null && !audios.isEmpty()){
				AudioView view = new AudioView();
				view.setUserId(audios.get(0).getUserId());
				view.setVersion(audios.get(0).getVersion());
				List<AudioItemView> audioItemViews = new ArrayList<AudioItemView>();
				for(Audio audio : audios){
					AudioItemView audioItemView = new AudioItemView();
					audioItemView.setId(audio.getId());
					audioItemView.setAudioName(audio.getAudioName());
					audioItemView.setAudioUrl(audio.getAudioUrl());
					if(audio.getAudioTextUrl()!=null && !"".equals(audio.getAudioTextUrl())){
						audioItemView.setAudioTextUrl(audio.getAudioTextUrl());
					}
					if(audio.getAudioTextName()!=null &&!"".equals(audio.getAudioTextName())){
						audioItemView.setAudioTextName(audio.getAudioTextName());
					}
					audioItemViews.add(audioItemView);
				}
				view.setAudios(audioItemViews);
				data.setAudio(view);
			}
			result.setCode(ErrorCode.OK);
			result.setDesc(ErrorCode.OK_DESC);
			result.setData(data);
			log.error("获取成功");
			return  ((JSONObject)JSON.toJSON(result)).toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("deleteRegistCode error|code:"+ErrorCode.UNKNOW+"|desc:"+ErrorCode.UNKNOW_DESC);
			result.setCode(ErrorCode.UNKNOW);
			result.setDesc(ErrorCode.UNKNOW_DESC);
			log.error("服务端出错："+e.getMessage());
			return  ((JSONObject)JSON.toJSON(result)).toJSONString();
		}
	}
	
	@RequestMapping(value="/uploadCodeDb", method=RequestMethod.POST)
	public @ResponseBody String uploadCodeDb(HttpServletRequest request,int version,long userId) {
		log.info("---------------uploadCodeDb start-------------------");
		JSONObject result = new JSONObject();
		if(version <= 0 || userId <= 0){
			result.put("code",ErrorCode.PARAMETER_ERROR);
			result.put("desc", ErrorCode.PARAMETER_ERROR_DESC);
			return result.toJSONString();
		}
		User fUser = new User();
		fUser.setId(userId);
		List<User> users = userServiceImpl.findRegistCode(fUser);
		if(users == null || users.size() < 1){
			result.put("code",ErrorCode.PARAMETER_NOUSER_ERROR);
			result.put("desc", ErrorCode.PARAMETER_NOUSER_ERROR_DESC);
			log.error("木有找到用户");
			return result.toJSONString();
		}
		fUser = users.get(0);
		if(version < fUser.getCodeDbVersion()){
			result.put("code",ErrorCode.VERSION_SMALL_ERROR);
			result.put("desc", ErrorCode.VERSION_SMALL_ERROR_DESC);
			log.error("上传的版本小于之前的版本");
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
	                    String path=codeDir+File.separator+userId;
	                    FileUtils.forceMkdir(new File(path));
	                    FileUtils.cleanDirectory(new File(path));
	                    //上传
	                    file.transferTo(new File(path+File.separator+file.getOriginalFilename()));
	                    fUser.setCodeDbUrl(Tool.getDownLoadUrl()+"/downloadCode/"+userId);
	                    fUser.setCodeDbName(file.getOriginalFilename());
	                    fUser.setPath(path+File.separator+file.getOriginalFilename());
	                    fUser.setCodeDbVersion(version);
	            		fUser.setCodeDbLastUpdateTime(System.currentTimeMillis());
	                    int flag = userServiceImpl.updateRegistCode(fUser);
	            		if( flag > 0){
	            			log.info("---------------uploadCodeDb end-------------------");
	            			result.put("code", ErrorCode.OK);
	            			result.put("desc", ErrorCode.OK_DESC);
	            			return result.toJSONString();
	            		}else{
	            			result.put("code", ErrorCode.UPDATE_ERROR);
	            			result.put("desc", ErrorCode.UPDATE_ERROR_DESC);
	            			log.error("更新失败");
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
	

   @RequestMapping("/downloadCode/{id}")
    public ResponseEntity<byte[]> download(@PathVariable long id) throws IOException {
	    try {
	    	if(id <= 0 ){
	    		return new ResponseEntity<byte[]>(null,null, HttpStatus.NOT_FOUND);
	    	}
	    	User fUser = new User();
			fUser.setId(id);
			List<User> users = userServiceImpl.findRegistCode(fUser);
	    	 if(users != null && !users.isEmpty()){
	    		 
	    		 HttpHeaders headers = new HttpHeaders();
    	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	        headers.setContentDispositionFormData("attachment", users.get(0).getCodeDbName());
    	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File( users.get(0).getPath())),
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