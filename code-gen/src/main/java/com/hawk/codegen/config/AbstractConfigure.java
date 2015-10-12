package com.hawk.codegen.config;

import java.io.File;

public class AbstractConfigure implements IProjectConfigure {

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRootPackageDirPath() {
		return rootPackageDirPath;
	}

	public String getRootPackage() {
		return rootPackage;
	}

	public void setRootPackage(String rootPackage) {
		this.rootPackage = rootPackage;
		String[] strArray = rootPackage.split(".");

		this.rootPackageDirPath = strArray[0];
		for (int i = 1; i < strArray.length; i++) {
			this.rootPackageDirPath = this.rootPackageDirPath + File.separator + strArray[i];
		}
	}

	//
	public String getResourceDirPath() {
		return resourceDirPath;
	}

	public String getSourceCodeDirPath() {
		return sourceCodeDirPath;
	}

	public void setSourceCodeDirPath(String sourceCodeDirPath) {
		this.sourceCodeDirPath = sourceCodeDirPath;
	}

	public String getSubPackage() {
		return subPackage;
	}

	public void setSubPackage(String subPackage) {
		this.subPackage = subPackage;

		String[] strArray = subPackage.split(".");

		this.subPackageDirPath = strArray[0];
		for (int i = 1; i < strArray.length; i++) {
			this.subPackageDirPath = this.subPackageDirPath + File.separator + strArray[i];
		}
	}

	public void clearDirectory(String directory) {
		File file = new File(directory);
		if (file.isDirectory()) {
			String[] f = file.list();
			for (String str : f) {
				File f1 = new File(file + File.separator + str);
				f1.delete();
			}
		}

	}

	private String projectName = null;
	private String projectDirPath = null;

	public String getProjectDirPath() {
		if (projectDirPath == null) {

			String baseDir = System.getProperty("user.dir");
			System.out.println(baseDir);
			String[] strArray = baseDir.split("\\\\");
			String dirPath = strArray[0];
			for (int i = 1; i < strArray.length - 1; i++) {
				dirPath = dirPath + File.separator + strArray[i];
			}
			projectDirPath = dirPath + File.separator + this.projectName;
		}

		return projectDirPath;
	}

	private String rootPackage;

	private String rootPackageDirPath;

	private String subPackage;

	private String subPackageDirPath;

	public String getSubPackageDirPath() {
		return subPackageDirPath;
	}

	private String sourceCodeDirPath = "src" + File.separator + "main" + File.separator + "java";

	private String resourceDirPath = "src" + File.separator + "main" + File.separator + "resources";

	public String computeEntireSourceCodeDir() {
		String entireSourceCodeDir = getProjectDirPath() + File.separator + getSourceCodeDirPath() + File.separator + getRootPackageDirPath() + File.separator
				+ getSubPackageDirPath();
		return entireSourceCodeDir;
	}

	public String computeEntireResourceDir() {
		String entireRecourceDir = getProjectDirPath() + File.separator + getResourceDirPath() + File.separator + getRootPackageDirPath() + File.separator
				+ getSubPackageDirPath();
		return entireRecourceDir;
	}

	//
}
