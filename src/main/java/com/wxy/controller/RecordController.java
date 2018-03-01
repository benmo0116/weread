package com.wxy.controller;

import com.wxy.model.Record;
import com.wxy.service.BookService;
import com.wxy.service.RecordService;
import com.wxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author wxy
 * @create 2018-01-19 09:55
 * @desc ${DESCRIPTION}
 **/
@Controller
@RequestMapping(value = "/record")
public class RecordController {
    @Autowired
    RecordService recordService;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @RequestMapping("/add")
    public String addRecord(HashMap<String, Object> map) {
        map.put("booklist", bookService.findAll(1, 100));
        return "/addrecord";
    }

    @ModelAttribute
    Record setRecord() {
        return new Record();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveRecord(@Valid @ModelAttribute(value = "record") Record record, BindingResult result, HashMap<String, Object> map) {
        if (result.hasErrors()) {
            for (ObjectError objectError : result.getAllErrors()) {
                System.out.println("ERROR:" + objectError.toString());
            }
            map.put("MSG", "出错啦！");
        } else if (!userService.exist(record.getUserid())) {
            result.addError(new ObjectError("record","该用户id不存在"));
            map.put("MSG", "出错啦！该用户id不存在");
        } else if (!bookService.exist(record.getBookid())) {
            result.addError(new ObjectError("record","该书籍id不存在"));
            map.put("MSG", "出错啦！该书籍id不存在");
        } else {
            if (record.getRecordtime() == null)
                record.setRecordtime(new Date());
            recordService.insertOne(record);
            map.put("MSG", "提交成功！");
        }
        map.put("booklist", bookService.findAll(1, 100));
        return "/addrecord";
    }
}
