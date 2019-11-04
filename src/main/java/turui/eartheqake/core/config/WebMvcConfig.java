package turui.eartheqake.core.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter  {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = System.getProperty("user.dir") + "/src/main/resources/static/";
        String os = System.getProperty("os.name");
        System.out.println(System.getProperty("os.name"));
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/static/**").
                    addResourceLocations("file:"+path);
        }
        super.addResourceHandlers(registry);
    }
}
