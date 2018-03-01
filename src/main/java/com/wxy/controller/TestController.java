package com.wxy.controller;

import com.wxy.model.User;
import com.wxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

/**
 * @author wxy
 * @create 2018-01-11 11:33
 * @desc 测试专用controller
 **/
@Controller
@RequestMapping("/test")
public class TestController extends BaseTestController{
    //   实现资源的注入
//    @PropertySource("") 注入配置配件需要使用@PropertySource指定文件地址  不支持加载yaml文件
    @Value("${project.name}")
    private String projectName;
    @Value("${project.author}")
    private String projectAuthor;


    @Autowired
    UserService userService;

    /**
     * GET模式下，这里使用了@PathVariable绑定输入参数，非常适合Restful风格。因为隐藏了参数与路径的关系，可以提升网站的安全性，静态化页面，降低恶意攻击风险。
     * POST模式下，使用@RequestBody绑定请求对象，Spring会帮你进行协议转换，将Json、Xml协议转换成你需要的对象。
     *
     * @ResponseBody可以标注任何对象，由Srping完成对象——协议的转换。
     */
    //@RequestBody  将HTTP请求正文转换为适合的HttpMessageConverter对象。
    //@ResponseBody 将内容或对象作为 HTTP 响应正文返回，并调用适合HttpMessageConverter的Adapter转换对象，写入输出流。
    @ResponseBody
    @RequestMapping("/")
    public String index() {
        return "欢迎来到" + projectName + ",作者:" + projectAuthor;
    }

    @RequestMapping("/index")
    public String toindex(HashMap<String, Object> map) {
        map.put("hello", "hello");
        return "/index";
    }

    @RequestMapping("/test")
    public String test(HashMap<String, Object> map) {
        map.put("hello", "hello");
        return "/test";
    }

    @RequestMapping("/interpret")
    public String interpret(HashMap<String, Object> map) {
        return "/interpret";
    }

    //    =======================================================================================================
    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public List<User> findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return userService.findAll(pageNum, pageSize);
    }

    //    文件上传
    @ResponseBody
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String fileUpload2(MultipartFile file, String str, HttpServletRequest request) throws IOException {
//        String path = request.getSession().getServletContext().getRealPath("upload");
        String path = remotePath + uploadPath;
        String fileName = file.getOriginalFilename();//获取到上传文件的名字
        print(path);
        print(fileName);
        //System.out.println(file.getName()+"2222");获取到file
        //file.getSize();获取到上传文件的大小
        File parent = new File(path);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        File dir = new File(path, fileName);
        //MultipartFile自带的解析方法
        file.transferTo(dir);
        return fileName;
    }

    /**
     * path 文件路径
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        InputStream is = null;
        OutputStream os = null;
        String path = request.getParameter("path");   //文件路径
        String fileName = request.getParameter("fileName");
        print("下载path:" + path);
        print("下载fileName:" + fileName);
        File file = file(remotePath + downloadPath + path);//下载源地址
        if (file.exists()) {
            print("文件存在" + file.getAbsolutePath());
        }
        if (fileName.isEmpty()) {
            fileName = "temp" + extName(file);
            print("下载fileName:temp:" + fileName);
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            is = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建File对象，自动识别相对或绝对路径，相对路径将自动从ClassPath下寻找
     *
     * @param path 文件路径
     * @return File
     */
    public File file(String path) {
        if (path.isEmpty()) {
            throw new NullPointerException("File path is blank!");
        }
        return new File(getAbsolutePath(path));
    }

    /**
     * 获取绝对路径，相对于classes的根目录<br>
     * 如果给定就是绝对路径，则返回原路径，原路径把所有\替换为/
     *
     * @param path 相对路径
     * @return 绝对路径
     */
    public String getAbsolutePath(String path) {
        if (path == null) {
            path = "";
        } else {
            path = normalize(path);
            print("normalize:-" + path);
            if (path.startsWith("/") || path.matches("^[a-zA-Z]:/.*")) {
                // 给定的路径已经是绝对路径了
                print("getAbsolutePath:-" + path);
                return path;
            }
        }
        // 相对路径
        ClassLoader classLoader = getClassLoader();
        URL url = classLoader.getResource(path);
        String reultPath = url != null ? url.getPath() : getClassPath() + path;
        // return StrKit.removePrefix(reultPath, PATH_FILE_PRE);
        print("相对路径:-" + path);
        return reultPath;
    }

    /**
     * 修复路径<br>
     * 1. 统一用 / <br>
     * 2. 多个 / 转换为一个
     *
     * @param path 原路径
     * @return 修复后的路径
     */
    public static String normalize(String path) {
        return path.replaceAll("[/\\\\]{1,}", "/");
    }

    /**
     * 获取文件扩展名
     *
     * @param file 文件
     * @return 扩展名
     */
    public static String extName(File file) {
        if (null == file) {
            return null;
        }
        if (file.isDirectory()) {
            return null;
        }
        return extName(file.getName());
    }

    /**
     * 获得文件的扩展名
     *
     * @param fileName 文件名
     * @return 扩展名
     */
    public static String extName(String fileName) {
        if (fileName == null) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return "";
        } else {
            String ext = fileName.substring(index + 1);
            // 扩展名中不能包含路径相关的符号
            return (ext.contains(String.valueOf('/')) || ext.contains(String.valueOf('\\'))) ? "" : ext;
        }
    }
//=====================================clazz

    /**
     * 获得ClassPath
     *
     * @return ClassPath
     */
    public static String getClassPath() {
        return getClassPathURL().getPath();
    }

    /**
     * 获得ClassPath URL
     *
     * @return ClassPath URL
     */
    public static URL getClassPathURL() {
        return getURL("");
    }

    /**
     * 获得资源的URL
     *
     * @param resource 资源（相对Classpath的路径）
     * @return 资源URL
     */
    public static URL getURL(String resource) {
        return getClassLoader().getResource(resource);
    }

    /**
     * @return 当前线程的class loader
     */
    public static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获得class loader<br>
     * 若当前线程class loader不存在，取当前类的class loader
     *
     * @return 类加载器
     */
    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = getContextClassLoader();
        if (classLoader == null) {
            classLoader = TestController.class.getClassLoader();
        }
        return classLoader;
    }
}
