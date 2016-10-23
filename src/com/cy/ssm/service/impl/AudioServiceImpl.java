package com.cy.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.ssm.beans.Audio;
import com.cy.ssm.mapper.AudioMapper;
import com.cy.ssm.service.IAudioService;
@Service
public class AudioServiceImpl implements IAudioService{
	
	@Resource
	private AudioMapper audioMapper;

	public AudioMapper getAudioMapper() {
		return audioMapper;
	}
	public void setAudioMapper(AudioMapper audioMapper) {
		this.audioMapper = audioMapper;
	}

	@Override
	public List<Audio> findAudioByUserId(long userId) throws Exception {
		List<Audio>  audios = null;
		try {
			audios = audioMapper.findAudioByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return audios;
	}


	@Override
	public Audio findAudioById(String id) throws Exception {
		Audio audio = null;
		try {
			audio = audioMapper.findAudioById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return audio;
	}


	@Override
	public int updateAudio(Audio audio) throws Exception {
		int flag = 0;
		try {
			flag = audioMapper.updateAudio(audio);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return flag;
	}


	@Override
	public int deleteAudioByUserId(long userId) throws Exception {
		int flag = 0;
		try {
			flag = audioMapper.deleteAudioByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return flag;
	}


	@Override
	public int deleteAudioById(String id) throws Exception {
		int flag = 0;
		try {
			flag = audioMapper.deleteAudioById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return flag;
	}
	@Override
	public int addAudio(Audio audio) throws Exception {
		int flag = 0;
		try {
			flag = audioMapper.addAudio(audio);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return flag;
	}

}
