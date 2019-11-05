package turui.eartheqake.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import turui.eartheqake.constant.Constant;
import turui.eartheqake.core.domain.UserDomain;
import turui.eartheqake.util.MapUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 入口controller
 */
@RestController
@CrossOrigin
public class IndexController {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    UserDomain userDomain;

    @GetMapping("/")
    public Map<String, Object> index(HttpServletRequest request)
    {
        return  this.Site(request);
    }

    @PostMapping("/")
    public Map<String, Object> index2(HttpServletRequest request)
    {
        return  this.Site(request);
    }

    /**
     * 核心入口方法
     * @param request
     * @return
     */
    private Map<String, Object> Site(HttpServletRequest request){
        String ac = request.getParameter("ac");
        String s = request.getParameter("s");
        if(s == null || ac == null || s.equals("") || ac.equals("")) {
            return MapUtil.requestMap(null, Constant.BAD_AC);
        }

        //开始接口调用
        try {
//            //必须参数检验
//            if(!s.equals("user") && !ac.equals("Login") && !ac.equals("Session"))
//            {
//                Map<String, Object> sessionMap = userDomain.Session(request);
//                if(sessionMap == null)
//                {
//                    return MapUtil.requestMap(null, Constant.BAD_AC,Constant.BAD_REQUEST);
//                }
//                if(sessionMap.get("data") == null)
//                {
//                    return MapUtil.requestMap(null, Constant.NOT_SUCCESS_KEEP_LOGIN,Constant.BAD_REQUEST);
//                }
//                Session session = (Session) sessionMap.get("data");
//                request.setAttribute("uuid", session.getUuid());
//            }


            Class<?> doClass = Class.forName("turui.eartheqake.core.domain."+ s +"Domain");
            Method doMethod = doClass.getMethod(ac,HttpServletRequest.class);//得到方法对象,有参的方法需要指定参数类型
            Map<String, Object> data=(Map<String, Object>)doMethod.invoke(this.applicationContext.getBean(doClass),request);
            if(data == null)
            {
                return MapUtil.requestMap(Constant.BAD_AC,Constant.BAD_REQUEST);
            }
            return data;

        }catch (Exception e){
            e.printStackTrace();
            return MapUtil.requestMap(Constant.BAD_AC, Constant.BAD_REQUEST);
        }
    }


}
