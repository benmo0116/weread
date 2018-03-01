package com.wxy.controller;

import com.wxy.sensitive.SensitivewordFilter;
import com.wxy.utils.office2html.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * @author wxy
 * @create 2018-01-31 10:58
 * @desc ${DESCRIPTION}
 **/
@Controller
@RequestMapping("/test2")
public class Test2Controller extends BaseTestController {
    /**
     * 文档预览
     *
     * @param mm
     * @return
     */
    @RequestMapping("/browse" + "/{type}")
    public String getBrowse(@PathVariable String type, ModelMap mm) {

        String cst = remotePath;//获取物理路径
        String res_url = "";
        switch (type) {
            case "txt":
                res_url = "txt.txt";
                break;
            case "doc":
                res_url = "doc.doc";
                break;
            case "docx":
                res_url = "docx.docx";
                break;
            case "ppt":
                res_url = "ppt.ppt";
                break;
            case "xls":
                res_url = "xls.xls";
                break;
            default:
                break;
        }
        String cstUrl = cst + "/" + res_url;//获取资源的物理路径

        //文档类转html的方法
        print("cstUrl:" + cstUrl);
        String htmlPath = FileUtil.getHtml(cstUrl, res_url);
        String fileHtml = htmlPath.replaceFirst(cst, "");//去掉物理路径
        print("htmlPath:" + htmlPath);
        print("fileHtml:" + fileHtml);
        mm.put("fileHtml", fileHtml);
        mm.put("title", res_url);
        return "/browse";
    }

//=====================================敏感词过滤==========================================================
    @Autowired
    public SensitivewordFilter filter;

    /**
     * 拦截敏感词
     *
     * @param content
     * @return
     */
    protected Set<String> wordFilter(String content) {
        int num = 0;//记录敏感词数目
        boolean flag = filter.isContaintSensitiveWord(content, 2);
        if (flag) {
            Set<String> set = filter.getSensitiveWord(content, 1);
            System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
            num = set.size();
            return set;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/word", method = RequestMethod.POST)
    public void testWord(String word) {
        if (word == null || word.isEmpty()) {
            print("待测试句子为空");
        }
        Set<String> set = wordFilter(word);
        print("待测试句子：" + word);
        if (set != null){
            print("语句中包含敏感词的个数为：" + set.size() + "\n包含：" + set);
            //通过websocket发送个通知
            sendNotice("语句中包含敏感词的个数为：" + set.size() + "\n包含：" + set);
        }
    }
//=====================================================================================

}
