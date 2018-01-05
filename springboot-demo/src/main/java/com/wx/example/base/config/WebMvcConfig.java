package com.wx.example.base.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wx.example.base.interceptor.MyInterceptor1;
import com.wx.example.base.interceptor.MyInterceptor2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by wangxiong on 2017/12/20.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 拦截器配置
     * 只有经过DispatcherServlet的请求，才会走拦截器链，我们自定义的Servlet请求是不会被拦截的，
     * 比如我们自定义的Servlet地址http://localhost:8080/myServlet1 是不会被拦截器拦截的。
     * 并且不管是属于哪个Servlet只要符合过滤器的过滤规则，过滤器都会拦截
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 使用FastJson解析Json数据
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(fastConverter);
    }

    /**
     * 使用代码来定义静态资源的映射
     * 增加/myResources/*映射到 classpath:/myResources/*
     * 这样使用代码的方式自定义目录映射，并不影响Spring Boot的默认映射，可以同时使用。
     * 如果我们将/myResources/*修改为 /* 与默认的相同时，则会覆盖系统的配置，可以多次使用 addResourceLocations添加目录，优先级先添加的高于后添加的。
     * 其中addResourceLocations的参数是动参，可以这样写addResourceLocations(“classpath:/img1/”, “classpath:/img2/”,“classpath:/img3/”);
     *
     * 如果我们要指定一个绝对路径的文件夹（如 D:/data/api_files），则只需要使用 addResourceLocations指定即可。
     * 可以直接使用addResourceLocations指定磁盘绝对路径，同样可以配置多个位置，注意路径写法需要加上file:
     * registry.addResourceHandler("/api_files/**").addResourceLocations("file:D:/data/api_files");
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/myResources/**").addResourceLocations("classpath:/myResources/");
        super.addResourceHandlers(registry);

    }

}
