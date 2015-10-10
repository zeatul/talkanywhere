package com.hawk.codegen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.codegen.config.IConfig;
import com.hawk.codegen.meta.Table;
import com.hawk.codegen.service.DatabaseParser;
import com.hawk.codegen.service.DomainHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class AppTaw {

	private static Logger logger = LoggerFactory.getLogger(DatabaseParser.class);

	private static Configuration cfg = new Configuration();
	static {
		cfg.setClassForTemplateLoading(AppTaw.class, "");
	}

	public static void main(String[] args) {
		public final static String ROOT_DIRECTORY = "C://mydata//workspace//workspace_weixin//cinema-server//weixin-system-service//";
	}
	
	private static String getBaseDirectory(){
		String baseDir = System.getProperty("user.dir");
		System.out.println(baseDir);
		String[] strArray = baseDir.split("\\\\");
		String dirPath = strArray[0];
		for (int i=1; i< strArray.length - 1 ; i++){
			dirPath = dirPath + File.separator + strArray[i];
		}
		return dirPath;
	}

	private static void genUser() {
		Configure configure = new Configure();
		//设置自有变量
		DomainHelper domainHelper = new DomainHelper(configure);
	}
	
	private static  void clearDirectory(String directory){
		File file = new File(directory);
		if (file.isDirectory()){
			String[] f = file.list();
			for (String str : f){
				File f1 = new File(file + File.separator +str);
				f1.delete();
			}
		}
		
	}
	
	private static void writeDomain(Table table,DomainHelper domainHelper) throws IOException, TemplateException{
		
		/* 获取或创建模板*/
		Template template = cfg.getTemplate("templates/domain.ftl");
		/* 创建数据模型 */
		Map<String,Object> root = domainHelper.transfer(table);
		/* 将模板和数据模型合并 */
		String className = root.get("className").toString();
		String packageName = root.get("moduleName").toString();
		
		String filePath = "";
		String directory ="";

		directory = rootDirectory + codeDirectory+rootPackageDirectory + packageName + "//domain";
		
		filePath = directory+"/"+className+".java";
		FileOutputStream fileOutputStream = new FileOutputStream(filePath,false);
		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8"); 
		template.process(root, out);
		out.flush();
		out.close();
		
	}
	
	
	private static void writeMapper(Table table) throws TemplateException, IOException{
		/* 获取或创建模板*/
		Template template = cfg.getTemplate("templates/mapper.ftl");
		/* 创建数据模型 */
		Map<String,Object> root = domainHelper.transfer(table);
		/* 将模板和数据模型合并 */
		String className = root.get("className").toString();
		String packageName = root.get("moduleName").toString();
		
		String filePath = "";
		String directory ="";

		directory = rootDirectory + codeDirectory+rootPackageDirectory + packageName + "//mapper";
		
		filePath = directory+"/"+className+"Mapper.java";
		FileOutputStream fileOutputStream = new FileOutputStream(filePath,false);
		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8"); 
		template.process(root, out);
		out.flush();
		out.close();
	}
	
	private static void writeMybatisr(Table table) throws TemplateException, IOException{
		/* 获取或创建模板*/
		Template template = cfg.getTemplate("templates/mybatis_"+Config.DIALECT+".ftl");
		/* 创建数据模型 */
		Map<String,Object> root = domainHelper.transfer(table);
		/* 将模板和数据模型合并 */
		String className = root.get("className").toString();
		String packageName = root.get("moduleName").toString();
		
		String filePath = "";
		String directory = "";

		
		directory = rootDirectory + resourceDirectory+rootPackageDirectory + packageName + "//mapper";
		
		filePath = directory+"/"+className+"Mapper.xml";
		FileOutputStream fileOutputStream = new FileOutputStream(filePath,false);
		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8"); 
		template.process(root, out);
		out.flush();
		out.close();
	}

	private static class Configure implements IConfig {

		private String package;

		

		/**
		 * database
		 */
		public static String DRIVER = "org.gjt.mm.mysql.Driver";
		public static String URL = "jdbc:mysql://127.0.0.1:3306/weixin-cinema?useUnicode=true&characterEncoding=utf-8";
		public static String USER = "root";
		public static String PASSWORD = "password";
		public static String SCHEMA = "weixin-cinema";
		// public static String FILTER = "T_CINEMA%";
		public static String FILTER = "t_um%";
		public static String DIALECT = "mysql";

		public String getDriver() {
			return DRIVER;
		}

		public String getUrl() {
			return URL;
		}

		public String getUser() {
			return USER;
		}

		public String getPassword() {
			return PASSWORD;
		}

		public String getSchema() {
			return SCHEMA;
		}

		public String getFilter() {
			return FILTER;
		}

		public String getDialect() {
			return DIALECT;
		}

	}

}
