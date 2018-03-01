package com.wxy.controller;

import com.sun.istack.internal.Nullable;
import com.wxy.model.User;
import com.wxy.service.UserService;
import com.wxy.shiro.ShiroKit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wxy
 * @create 2018-02-24 15:37
 * @desc ${DESCRIPTION}
 **/
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    UserService userService;

    //登录页面
    @RequestMapping("")
    public String toLogin() {
        return "/login";
    }

//    @Resource默认是按照名称来装配注入的，只有当找不到与名称匹配的bean才会按照类型来装配注入；
//    @Autowired默认是按照类型装配注入的，如果想按照名称来转配注入，则需要结合@Qualifier一起使用；
//    @Resource注解是由JDK提供，而@Autowired是由Spring提供

    //配置Thymeleaf全局变量 ☆☆☆☆☆
//    @Resource
//    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
//        println("configureThymeleafStaticVars 配置thymeleaf静态变量");
//        if(viewResolver != null) {
//            Map<String, Object> vars = new HashMap<>();
//            vars.put("user", );
//            vars.put("var", "Hello World");
//            viewResolver.setStaticVariables(vars);
//        }
//    }
    @Resource(name="thymeleafViewResolver")
    ThymeleafViewResolver thymeleafViewResolver;


    //重定向方式一
//    @RequestMapping(value = "/",method = RequestMethod.POST)
//    public String login(String account,String password, HashMap<String, Object> map){
//        User user = userService.findByName(account);
//            if (user != null && user.getPassword().equals(password)){
//                //重定向 /index
//                return "redirect:/index";
//            }else {
//                map.put("MSG", "出错了！");
//                return "/login";
//            }
//    }

    //重定向方式二 在@RestController时重定向方法
//    @RequestMapping("/")
//    public void login(HttpServletResponse response){
//        //重定向 /index
//        try {
//            response.sendRedirect("/index");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 登陆地址
     */
    @GetMapping("/")
    public String login() {
        if (ShiroKit.isAuthenticated()) {
            return "redirect:/index";
        }
        return "/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(String account, String password, @Nullable Boolean rememberMe, HashMap<String, Object> map) {
        System.out.println(account);
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        if (rememberMe != null){
            token.setRememberMe(rememberMe);
        }else {
            token.setRememberMe(false);
        }
        token.setRememberMe(false);
        Subject currentUser = ShiroKit.getSubject();
        try {
            currentUser.login(token);
            Session session = ShiroKit.getSession();
            println("sessionID	: {} ", session.getId());
            println("sessionHost	: {}", session.getHost());
            println("sessionTimeOut	: {}", session.getTimeout());
        } catch (UnknownAccountException e) {
            println("账号不存在!", e);
            map.put("MSG", "账号不存在！");
            return "/login";
        } catch (DisabledAccountException e) {
            println("账号未启用!", e);
            map.put("MSG", "账号未启用！");
            return "/login";
        } catch (IncorrectCredentialsException e) {
            println("密码错误!", e);
            map.put("MSG", "密码错误！");
            return "/login";
        } catch (RuntimeException e) {
            println("未知错误,请联系管理员!", e);
            map.put("MSG", "未知错误,请联系管理员!！");
            return "/login";
        }
//        doLog(ShiroKit.getSession(), "登录");
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        println(principal.toString());
        //添加全局静态变量
        if (thymeleafViewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            vars.put("shirouser", principal);
            thymeleafViewResolver.setStaticVariables(vars);
        }
        return "redirect:/index";
    }

    @ResponseBody
    @RequestMapping(value = "/u", method = RequestMethod.GET)
    public User getPrincipal() {
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        return principal;
    }

    public void print(Object str) {
        System.out.print("PRINT: " + str);
    }

    public void println(Object str) {
        System.out.println("PRINT: " + str);
    }

    public void println(Object str1, Object str2) {
        System.out.println("PRINT:: " + str1 + ":" + str2);
    }
}
