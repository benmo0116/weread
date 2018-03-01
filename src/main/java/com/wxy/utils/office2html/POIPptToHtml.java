package com.wxy.utils.office2html;

import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.xslf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author ruand http://blog.csdn.net/emoven/article/details/52683215
 */
public class POIPptToHtml {

	private final static String PPT = "ppt";
	private final static String PPTX = "pptx";

	public synchronized static void pptToHtml(String sourcePath,String picturesPath, String targetDir,String cstImgUrl) {
		//判断html文件是否存在
		File htmlFile=new File(targetDir); 
		if(htmlFile.exists()){
			System.out.println("htmlFile.exists()");
			return;
		}
		
		File pptFile = new File(sourcePath);
		if (pptFile.exists()) {
			try {
				String type = FileUtil.GetFileExt(sourcePath);
				

				if (PPT.equals(type)) {
					String htmlStr = toImage2003(sourcePath,picturesPath, targetDir,cstImgUrl);
					FileUtil.writeFile(htmlStr, targetDir);
				} else if (PPTX.equals(type)) { 					
					String htmlStr = toImage2007(sourcePath,picturesPath, targetDir, cstImgUrl);
					FileUtil.writeFile(htmlStr, targetDir);
				} else {
					System.out.println("the file is not a ppt");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("file does not exist!");
		}

	}

	public static String toImage2007(String sourcePath,String picturesPath, String targetDir,String cstImgUrl) throws Exception {
		String htmlStr = "";
		FileInputStream is = new FileInputStream(sourcePath);
		XMLSlideShow ppt = new XMLSlideShow(is);
		is.close();
		FileUtil.createHtmlDir(targetDir);// create html dir
		Dimension pgsize = ppt.getPageSize();
		System.out.println(pgsize.width + "--" + pgsize.height);

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ppt.getSlides().size(); i++) {
			try {
				// 防止中文乱码
				for (XSLFShape shape : ppt.getSlides().get(i).getShapes()) {
					if (shape instanceof XSLFTextShape) {
						XSLFTextShape tsh = (XSLFTextShape) shape;
						for (XSLFTextParagraph p : tsh) {
							for (XSLFTextRun r : p) {
								r.setFontFamily("宋体");
							}
						}
					}
				}
				BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = img.createGraphics();
				// clear the drawing area
				graphics.setPaint(Color.white);
				graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
				// render
				ppt.getSlides().get(i).draw(graphics);
				// save the output		
				
				String imageDir =picturesPath;
				FileUtil.createDir(imageDir);// create image dir
				String imagePath = imageDir +File.separator + (i + 1) + ".png";
				sb.append("<br>");
				String imagePath2=cstImgUrl+File.separator+(i+1)+".png";//显示图片的虚拟路径
				System.out.println("第" + i + "张图片虚拟路径：" + imagePath2);
				System.out.println("<img src=" + "\"" +  imagePath2 + "\"" + "/>");
				sb.append("<img src=" + "\"" +  imagePath2 + "\"" + "/>");
				
				FileOutputStream out = new FileOutputStream(imagePath);				
				ImageIO.write(img, "png", out);
				out.close();
			} catch (Exception e) {
				System.out.println("第" + i + "张ppt转换出错");
			}
		}
		System.out.println("success");
		htmlStr = sb.toString();

		return htmlStr;
	}

	public static String toImage2003(String sourcePath,String picturesPath, String targetDir,String cstImgUrl) {
		String htmlStr = "";
		try {
			HSLFSlideShow ppt = new HSLFSlideShow(new HSLFSlideShowImpl(sourcePath));
			FileUtil.createHtmlDir(targetDir);// create html dir
			Dimension pgsize = ppt.getPageSize();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ppt.getSlides().size(); i++) {
				// 防止中文乱码
				for (HSLFShape shape : ppt.getSlides().get(i).getShapes()) {
					if (shape instanceof HSLFTextShape) {
						HSLFTextShape tsh = (HSLFTextShape) shape;
						for (HSLFTextParagraph p : tsh) {
							for (HSLFTextRun r : p) {
								r.setFontFamily("宋体");
							}
						}
					}
				}
				BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = img.createGraphics();
				// clear the drawing area
				graphics.setPaint(Color.white);
				graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
				// render
				ppt.getSlides().get(i).draw(graphics);

				String imageDir =picturesPath;
				FileUtil.createDir(imageDir);// create image dir
				String imagePath = imageDir +File.separator + (i + 1) + ".png";
				sb.append("<br>");
				String imagePath2=cstImgUrl+File.separator+(i+1)+".png";//显示图片的虚拟路径
				sb.append("<img src=" + "\"" +  imagePath2 + "\"" + "/>");

				FileOutputStream out = new FileOutputStream(imagePath);
				ImageIO.write(img, "png", out);
				out.close();

			}
			System.out.println("success");
			htmlStr = sb.toString();
		} catch (Exception e) {

		}
		return htmlStr;
	}

	/***
	 * 功能 :调整图片大小
	 * 
	 * @param srcImgPath
	 *            原图片路径
	 * @param distImgPath
	 *            转换大小后图片路径
	 * @param width
	 *            转换后图片宽度
	 * @param height
	 *            转换后图片高度
	 */
	public static void resizeImage(String srcImgPath, String distImgPath, int width, int height) throws IOException {

		File srcFile = new File(srcImgPath);
		Image srcImg = ImageIO.read(srcFile);
		BufferedImage buffImg = null;
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		buffImg.getGraphics().drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

		ImageIO.write(buffImg, "JPEG", new File(distImgPath));

	}
}
