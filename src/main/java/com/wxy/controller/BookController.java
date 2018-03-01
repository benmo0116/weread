package com.wxy.controller;

import com.wxy.model.Book;
import com.wxy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * @author wxy
 * @create 2018-01-18 10:02
 * @desc ${DESCRIPTION}
 **/
@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    BookService bookService;
    @RequestMapping("/add")
    public String addBook(HashMap<String,Object> map){
        map.put("booklist",bookService.findAll(1,200));
        return "/addbook";
    }
    @ModelAttribute
    Book setBook () {
        return new Book ();
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveBook(@Valid @ModelAttribute(value="book") Book book, BindingResult result, HashMap<String, Object> map){
        if(result.hasErrors()){
//            for (ObjectError objectError:result.getAllErrors()) {
//                System.out.println("ERROR:"+objectError.toString());
//            }
            map.put("MSG", "出错啦！");
        }else{
            map.put("MSG", "提交成功！");
            bookService.insertOne(book);
        }
        map.put("booklist",bookService.findAll(1,200));
        return "/addbook";
    }
}
