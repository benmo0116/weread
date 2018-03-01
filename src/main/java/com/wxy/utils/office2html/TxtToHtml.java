package com.wxy.utils.office2html;
import org.apache.commons.io.FileUtils;
import java.io.File;

/**
 * txt文本转html
 * @author Administrator
 *
 */
public class TxtToHtml {
	
	
	/**
	 * txt转html
	 * @param filePath 原文件路径
	 * @param htmlPath 生成文件的路径
	 */
	public synchronized static void txtToHtml(String filePath,String htmlPath){
		File html=new File(htmlPath);
		if(html.exists()){//判断是否已经生成过
			return ;
		}
		try {
			File file=new File(filePath);
			if(file.isFile()&&file.exists()){//判断文件是否存在
				String encode=EncodingDetect.getJavaEncode(filePath); //获取文件的编码
				String content= FileUtils.readFileToString(file, encode);
				FileUtil.writeFile(content, htmlPath);
			}else{
				System.out.println("未找到原文件："+filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
