package com.wxy.utils.office2html;

import java.io.*;

public class FileUtil {
	private static final String ENCODING = "UTF-8";// UTF-8

	/**
	 * 获取文件的后缀
	 * @param path 
	 * @return
	 */
	public static String GetFileExt(String path) {
		String ext = null;
		int i = path.lastIndexOf('.');
		if (i > 0 && i < path.length() - 1) {
			ext = path.substring(i + 1);
		}
		return ext;
	}
	/**
	 * 获取文件名称
	 * @param path
	 * @return
	 */
	public static String GetFileName(String path){
		String ext=null;
		int i = path.lastIndexOf('.');
		int j= path.lastIndexOf('/');		
		ext=path.substring( j+1,i);
		return ext;
	}
	/**
	 * 获取文件路径
	 * @param path
	 * @return
	 */
	public static String GetFileUrl(String path){
		String ext=null;
		int i=path.lastIndexOf("/");		
		if(i>0&&i<path.length()-1){			
			ext=path.substring(0,i+1);
		}
		return ext;
	}

	public static void createHtmlDir(String path) {
		int i = path.lastIndexOf('/');
		String dirPath = "";
		if (i > 0 && i < path.length() - 1) {
			dirPath = path.substring(0, i);
		}
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

	}
	
	public static void createDir(String dirPath) {
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

	}

	public static void writeFile(String content, String path) {
		createHtmlDir(path);
		OutputStream os = null;
		BufferedWriter bw = null;
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			os = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(os, ENCODING));
			bw.write(content);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (os != null)
					os.close();
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}
	
	/**
	 * 判断html文件是否存在
	 * @param targetPath 文件生成后的路径
	 * @return 路径
	 */
	public static String checkHtmlFile(String targetPath){
		String htmlName=null;
		File htmlFile=new File(targetPath); //判断html文件是否存在
		if(htmlFile.exists()){
			htmlName= htmlFile.getAbsolutePath();
		}
		return htmlName;
	}
	
	
	
	/**
	 * 文档类转html
	 * @param filePath 资源的物理路径
	 * @param url2 数据库中存的路径
	 * @return
	 */
	public static String getHtml(String filePath,String url2){			
		final String HTMLDOC="html_doc/"; //文件生成的路径后缀
		final String HTMLXLS="html_xls/";
		final String HTMLPPT="html_ppt/";
		final String HTMLTXT="html_txt/";
		
		String url=GetFileUrl(filePath); //获取原文件的路径
		String name=GetFileName(filePath);//获取文件名称
		String suffix=GetFileExt(filePath);//获取文件的后缀			
		String imgFile=url+"/image";//磁盘上生成图片的物理路径	
		
		url2=GetFileUrl(url2);
		// TODO: 2018-01-31
		String cstImgUrl= (url2 == null?"/":url2) +"image";//图片展示虚拟路径
		System.out.println("PRINT:图片展示虚拟路径：" + cstImgUrl);
		String ext=".html";//生成文件的后缀
		
		String htmlPath=null;
		if("doc".equals(suffix)||"docx".equals(suffix)){
			htmlPath=url+HTMLDOC+name+ext;//生成html后的路径
			POIWordToHtml.wordToHtml(filePath,imgFile,htmlPath,cstImgUrl);
			
		}else if("xls".equals(suffix)||"xlsx".equals(suffix)){
			htmlPath=url+HTMLXLS+name+ext;
			POIExcelToHtml.excelToHtml(filePath,htmlPath,true);
			
		}else if("ppt".equals(suffix)||"pptx".equals(suffix)){
			htmlPath=url+HTMLPPT+name+ext;
			System.out.println("PRINT:pptToHtml:" + htmlPath);
			POIPptToHtml.pptToHtml(filePath,imgFile,htmlPath,cstImgUrl);
			
		}else if("txt".equals(suffix)){
			htmlPath=url+HTMLTXT+name+ext;
			TxtToHtml.txtToHtml(filePath, htmlPath);
		}
		return htmlPath;
	}
	
	/*public static String getHtml(String filePath,String url2){			
		final String html="html"; //文件生成的路径后缀
		
		String url=GetFileUrl(filePath); //获取原文件的路径
		String name=GetFileName(filePath);//获取文件名称
		String suffix=GetFileExt(filePath);//获取文件的后缀			
		String imgFile=url+"/image";//磁盘上生成图片的物理路径	
		
		url2=GetFileUrl(url2);
		String cstImgUrl=ConstConfig.DOMAIN+url2+"image";//图片展示虚拟路径
		String ext=".html";//生成文件的后缀
		
		String htmlPath=url+html+name+ext;//生成html后的路径		
		if("doc".equals(suffix)||"docx".equals(suffix)){			
			POIWordToHtml.wordToHtml(filePath,imgFile,htmlPath,cstImgUrl);
			
		}else if("xls".equals(suffix)||"xlsx".equals(suffix)){
			POIExcelToHtml.excelToHtml(filePath,htmlPath,true);
			
		}else if("ppt".equals(suffix)||"pptx".equals(suffix)){
			POIPptToHtml.pptToHtml(filePath,imgFile,htmlPath,cstImgUrl);
			
		}else if("txt".equals(suffix)){			
			TxtToHtml.txtToHtml(filePath, htmlPath);
		}
		return htmlPath;
	}
*/
}
