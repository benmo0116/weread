package com.wxy.utils.office2html;

import org.apache.commons.io.FileUtils;

import java.io.File;


public class Test {
	
	public static void main(String[] args) {
		
		POIWordToHtml.wordToHtml("F://预览测试/测试.doc",
				"F://officeToHtml/image",
				"F://officeToHtml/html_doc/测试.html",
				"虚拟路径");
		/*POIWordToHtml.wordToHtml("E://officeToHtml/值班日志模板.doc", "E://officeToHtml/image", "E://officeToHtml/html_doc/值班日志模板.html");
		POIExcelToHtml.excelToHtml("E://officeToHtml//201707考勤.xlsx", "E://officeToHtml/html_xls/201707考勤.html", true);
		POIExcelToHtml.excelToHtml("E://officeToHtml/12.15-12.21学士后课表.xls", "E://officeToHtml/html_xls/12.15-12.21学士后课表.html", true);
		POIPptToHtml.pptToHtml("E://officeToHtml/PPT演示2.pptx", "E://officeToHtml/html_ppt");
		POIPptToHtml.pptToHtml("E://officeToHtml/医院知识培训.ppt", "E://officeToHtml/html_ppt");*/
		
		try {
			//测试文本格式。兼容各种文本格式
			String text1="F:/预览测试/test.txt";
			String fileEncode = EncodingDetect.getJavaEncode(text1);
			String fileContent = FileUtils.readFileToString(new File(text1), fileEncode);
			System.out.println(fileEncode);
			System.out.println(fileContent);
			System.out.println("--------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
