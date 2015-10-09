package com.baoflag.hawk.config;

public interface IConfig {
	
	public String getDriver();
	public String getUrl();
	public String getUser();
	public String getPassword();
	public String getSchema();
	public String getFilter();
	public String getDialect();
	
	public String getRootPackage();
	
//	public static String DRIVER = "org.gjt.mm.mysql.Driver";
//	public static String URL = "jdbc:mysql://127.0.0.1:3306/weixin-cinema?useUnicode=true&characterEncoding=utf-8";
//	public static String USER = "root";
//	public static String PASSWORD = "123456";
//	public static String SCHEMA = "weixin-cinema";
//		public static String FILTER = "T_CINEMA%";
//		//			public static String FILTER = "T_SYSTEM%";
//	public static String DIALECT = "mysql";

}
