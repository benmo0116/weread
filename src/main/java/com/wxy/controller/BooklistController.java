package com.wxy.controller;

import com.wxy.model.BokList;
import com.wxy.service.BokListService;
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
@RequestMapping(value = "/booklist")
public class BooklistController {
    @Autowired
    BokListService bokListService;
    @RequestMapping("/add")
    public String addBookList(HashMap<String,Object> map){
        map.put("booklists",bokListService.findAll(1,35));
        return "/addbooklist";
    }
    @ModelAttribute
    BokList setBookList () {
        return new BokList ();
    }
    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveBokList(@Valid @ModelAttribute(value="bokList") BokList bokList, BindingResult result, HashMap<String, Object> map){
        if(result.hasErrors()){
            for (ObjectError objectError:result.getAllErrors()) {
                System.out.println("ERROR:"+objectError.toString());
            }
            map.put("MSG", "出错啦！");
        }else{
//            bokList.setListyear(2018);
            bokList.setListtime(new Date());
            bokList.setIffinished(false);
            bokListService.insertOne(bokList);
            map.put("MSG", "提交成功！");
        }
        map.put("booklists",bokListService.findAll(1,35));
        return "/addbooklist";
    }
}
