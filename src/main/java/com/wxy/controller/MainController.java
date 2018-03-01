package com.wxy.controller;

import com.wxy.model.BokList;
import com.wxy.model.Book;
import com.wxy.model.Record;
import com.wxy.model.User;
import com.wxy.service.BokListService;
import com.wxy.service.BookService;
import com.wxy.service.RecordService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @author wxy
 * @create 2018-01-11 16:09
 * @desc ${DESCRIPTION}
 **/
@Controller
@RequestMapping(value = "/")
public class MainController {
//    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private static final Logger logger = Logger.getLogger(MainController.class);
    @Autowired
    BookService bookService;
    @Autowired
    RecordService recordService;
    @Autowired
    BokListService bokListService;

    @RequestMapping("/index")
    public String toindex(HashMap<String,Object> map){
        map.put("booklists",bookService.findAll(1,12));
        map.put("recordlists",recordService.findAll(1,15));
        map.put("boklist",bokListService.findAll(1,7));

        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        map.put("username",principal.getNickname());
        logger.info("==================------------------------=============");
        return "/index";
    }

    @ResponseBody
    @RequestMapping("/book/list")
    public List<Book> getBookList(int pageNum, int pageSize,HashMap<String,Object> map){
        map.put("booklists",bookService.findAll(pageNum,pageSize));
        return bookService.findAll(pageNum,pageSize);
    }
    @ResponseBody
    @RequestMapping("/booklist/list")
    public List<BokList> getBokLists(int pageNum, int pageSize){
        return bokListService.findAll(pageNum,pageSize);
    }
    @ResponseBody
    @RequestMapping("/record/list")
    public List<Record> getRecordLists(int pageNum,int pageSize){
//        System.out.println(recordService.findAll(pageNum,pageSize).get(0).toString());
        return recordService.findAll(pageNum,pageSize);
    }
}
