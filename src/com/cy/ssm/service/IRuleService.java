package com.cy.ssm.service;


import com.cy.ssm.beans.Rule;

public interface IRuleService {

	public int addRule(Rule rule) throws Exception;
    
	public Rule findRule() throws Exception;
   
	public int updateRule(Rule rule) throws Exception;
	
}
