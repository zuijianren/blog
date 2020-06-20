package com.zuijianren.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e, RedirectAttributes attributes) throws Exception {
        //日志记录
        logger.error("Request URL : {}, Exception : {}", request.getRequestURL(), e);

        if((AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)) != null){
            throw e;
        }

        ModelAndView mv = new ModelAndView();

        String message = e.getMessage();
        System.out.println("===========e==========\n"+ message +"\n==========e==========");
        if(message.contains("SQLIntegrityConstraintViolationException")){
            //如果报该异常，说明是数据库的外键问题，即对应的type或tag有与之关联的blog未删除
            //将错误信息返回到上一个请求，并添加错误原因
            attributes.addFlashAttribute("warn", "仍有该对象相关的博客未删除，请检查后再执行删除操作");
            String referer = request.getHeader("Referer");
            System.out.println("referer:"+referer);
            int admin = referer.indexOf("admin");
            String addr = referer.substring(admin-1);
            mv.setViewName("redirect:"+addr);
            return mv;
        }

        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        //跳转到error文件夹下的error页面
        mv.setViewName("error");

        return mv;
    }
}
