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
	 * 未知异常
	 */
	public static final int UNKNOW = -1;
	public static final String UNKNOW_DESC = "未知异常";
	
	
	
	
}
