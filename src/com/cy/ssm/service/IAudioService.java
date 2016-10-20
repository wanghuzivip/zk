package com.cy.ssm.service;


import java.util.List;

import com.cy.ssm.beans.Audio;

public interface IAudioService {

	public int addAudio(Audio audio) throws Exception;
    
	public List<Audio> findAudioByUserId(long userId) throws Exception;
	
	public Audio findAudioById(long id) throws Exception;
   
	public int updateAudio(Audio audio) throws Exception;
    
	public int deleteAudioByUserId(long userId) throws Exception;
	
	public int deleteAudioById(long id) throws Exception;
	
}
