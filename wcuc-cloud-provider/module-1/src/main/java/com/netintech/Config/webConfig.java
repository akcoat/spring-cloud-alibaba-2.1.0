package com.netintech.Config;



import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
public class webConfig  implements WebMvcConfigurer  {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SeataHandlerInterceptor()).addPathPatterns(new String[]{"/**"});
    }
}
