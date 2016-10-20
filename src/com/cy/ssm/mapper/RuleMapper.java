package com.cy.ssm.mapper;

import com.cy.ssm.beans.Rule;

public interface RuleMapper {
   
	public int addRule(Rule rule) throws Exception;
    
	public Rule findRule() throws Exception;
   
	public int updateRule(Rule rule) throws Exception;
    

	
}
