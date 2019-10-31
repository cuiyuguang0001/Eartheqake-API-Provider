package turui.eartheqake.core.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import turui.eartheqake.core.adapter.LoginAdapter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    LoginAdapter loginAdapter;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        InterceptorRegistration in = registry.addInterceptor(loginAdapter);
//        in.addPathPatterns("/Site/*");
    }
}
