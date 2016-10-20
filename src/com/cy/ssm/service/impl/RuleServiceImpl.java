package com.cy.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.ssm.beans.Rule;
import com.cy.ssm.mapper.RuleMapper;
import com.cy.ssm.service.IRuleService;
@Service
public class RuleServiceImpl implements IRuleService{
	
	@Resource
	private RuleMapper ruleMapper;

	public RuleMapper getRuleMapper() {
		return ruleMapper;
	}
	public void setRuleMapper(RuleMapper ruleMapper) {
		this.ruleMapper = ruleMapper;
	}


	@Override
	public int addRule(Rule rule) throws Exception {
		int flag = 0;
		try {
			flag = ruleMapper.addRule(rule);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		return flag;
	}


	@Override
	public Rule findRule() throws Exception {
		Rule rule = null;
		try {
			rule = ruleMapper.findRule();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return rule;
	}


	@Override
	public int updateRule(Rule rule) throws Exception {
		int flag = 0;
		try {
			flag = ruleMapper.updateRule(rule);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		return flag;
	}

}
