package com.cy.ssm.constants;

public class ErrorCode {
	 
	/**
	 * 调用成功
	 */
	public static final int OK = 0;
	public static final String OK_DESC = "成功";
	
	/**
	 * 参数错误
	 */
	public static final int PARAMETER_ERROR = 1;
	public static final String PARAMETER_ERROR_DESC = "参数错误";
   
	/**
	 * 参数解析错误
	 */
	public static final int PARAMETER_PARSE_ERROR = 2;
	public static final String PARAMETER_PARSE_ERROR_DESC = "参数解析错误";
	
	/**
	 * 木有registCode
	 */
	public static final int PARAMETER_NOREGISTCODE_ERROR = 3;
	public static final String PARAMETER_NOREGISTCODE_ERROR_DESC = "木有registCode";
	
	/**
	 * 更新失败
	 */
	public static final int UPDATE_ERROR = 4;
	public static final String UPDATE_ERROR_DESC = "更新失败";
	
	/**
	 * add失败
	 */
	public static final int ADD_ERROR = 5;
	public static final String ADD_ERROR_DESC = "add失败";
	
	/**
	 * del失败
	 */
	public static final int DEL_ERROR = 6;
	public static final String DEL_ERROR_DESC = "del失败";
	
	
	/**
	 * addOrUpdate失败
	 */
	public static final int ADDORUPDATE_ERROR = 7;
	public static final String ADDORUPDATE_ERROR_DESC = "addOrUpdate失败";
	
	
	
	/**
	 * 木有userId
	 */
	public static final int PARAMETER_NOUSERID_ERROR = 8;
	public static final String PARAMETER_NOUSERID_ERROR_DESC = "木有userId";
	
	/**
	 * 用户不存在
	 */
	public static final int PARAMETER_NOUSER_ERROR = 9;
	public static final String PARAMETER_NOUSER_ERROR_DESC = "用户不存在";
	
	/**
	 * 未知异常
	 */
	public static final int UNKNOW = -1;
	public static final String UNKNOW_DESC = "未知异常";
	
	
	
	
}
