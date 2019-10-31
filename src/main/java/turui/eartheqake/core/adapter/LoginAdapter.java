package turui.eartheqake.core.adapter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginAdapter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("----------");
        System.out.println(request.getRequestURI());
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestURI());
        System.out.println(request.getQueryString());
        System.out.println(request.getAttribute("ac"));
        System.out.println(response.getHeader("ac"));

        if(request.getParameter("sid") != null)
        {
            return true;
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
