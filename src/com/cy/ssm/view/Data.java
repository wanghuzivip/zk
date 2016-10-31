package com.cy.ssm.view;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Data {
	private RuleView rule;
	private BackPicView backPic;
	private AudioView audio;
	private UserView user;
	public RuleView getRule() {
		return rule;
	}
	public void setRule(RuleView rule) {
		this.rule = rule;
	}
	public BackPicView getBackPic() {
		return backPic;
	}
	public void setBackPic(BackPicView backPic) {
		this.backPic = backPic;
	}
	public AudioView getAudio() {
		return audio;
	}
	public void setAudio(AudioView audio) {
		this.audio = audio;
	}
	public UserView getUser() {
		return user;
	}
	public void setUser(UserView user) {
		this.user = user;
	}
	public static void main(String[] args) {
		Result result = new Result();
		result.setCode(0);
		result.setDesc("xxx");
		
		Data data = new Data();
		AudioView view = new AudioView();
		List<AudioItemView> audioItemViews = new ArrayList<AudioItemView>();
		AudioItemView audioItemView = new AudioItemView();
		audioItemView.setAudioName("eeee");
		audioItemView.setAudioTextName("ffff");
		audioItemView.setAudioTextUrl("5555");
		audioItemView.setAudioUrl("rrrr");
		audioItemViews.add(audioItemView);
		view.setAudios(audioItemViews);
		view.setId("444");
		view.setUserId(44);
		data.setAudio(view);
		
		
		BackPicView backPic = new BackPicView();
		backPic.setId("22");
		backPic.setUserId(22);
		backPic.setVersion(22);
		backPic.setBackPics(null);
		data.setBackPic(backPic);
		data.setRule(null);
		data.setUser(null);
		
		result.setData(data);
		JSONObject json = (JSONObject) JSON.toJSON(result);
		System.out.println(json.toJSONString());
		Result result2 = JSON.toJavaObject(json, Result.class);
		System.out.println(result2.getCode());
		
	}
	
	
}
