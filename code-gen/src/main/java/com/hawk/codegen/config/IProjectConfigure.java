package com.hawk.codegen.config;

public interface IProjectConfigure {
	

	
	/**
	 * 
	 * @return 根packae
	 */
	public String getRootPackage(); //
	
	/**
	 * 
	 * @return //根packagePath
	 */
	public String getRootPackageDirPath(); 
	
	/**
	 * 
	 * @return 项目目录
	 */
	public String getProjectDirPath(); 
	
	/**
	 * 设置项目名称 根据这个定位代码生成的项目目录
	 * @param proejctName
	 */
	public void setProjectName(String proejctName); 
	
	/**
	 * 
	 * @return 子模块的package
	 */
	public String getSubPackage(); //
	
	/**
	 * 
	 * @return 子模块的package Path
	 */
	public String getSubPackageDirPath(); // 
	
	/**
	 * 
	 * @return src/main/java
	 */
	public String getSourceCodeDirPath(); //
	
	/**
	 * 
	 * @return src/main/resources
	 */
	public String getResourceDirPath();  
	
	/**
	 * 
	 * @return 完整的source code 目录
	 */
	public String computeEntireSourceCodeDir();
	
	/**
	 * 
	 * @return 完整的resource code目录
	 */
	public String computeEntireResourceDir();
	



}
