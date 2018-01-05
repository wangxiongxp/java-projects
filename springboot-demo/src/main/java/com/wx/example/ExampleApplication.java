package com.wx.example;

import com.wx.example.base.servlet.MyServlet1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import plugin.spring.SpringUtil;

import java.util.Locale;

/**
 * @SpringBootApplication 声明让spring boot自动给程序进行必要的配置，
 * 等价于以默认属性使用@Configuration，@EnableAutoConfiguration和@ComponentScan
 * @ServletComponentScan使的spring能够扫描到我们自己编写的servlet和filter
 * Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码
 */
@RestController
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages={"cn.wx", "org.wx", "com.wx"})
@EnableAsync//@Async注解能够生效
public class ExampleApplication {

	/**
	 * 通过代码注册Servlet，这里不需要添加注解：@ServletComponentScan
	 */
	@Bean
	public ServletRegistrationBean MyServlet1(){
		return new ServletRegistrationBean(new MyServlet1(),"/myServlet1/*");
	}

	/**
	 * 2、注册Spring Util
	 * 这里为了和上一个冲突，所以方面名为：springUtil2
	 * 实际中使用springUtil
	 * 3、第三种方法，
	 * 使用@Import(value={SpringUtil.class})引入
     */
	@Bean
	public SpringUtil springUtil2(){return new SpringUtil();}

	public static void main(String[] args) {
		//获取到ApplicationContext上下文
		ApplicationContext ctx = SpringApplication.run(ExampleApplication.class, args);

//		String[] beanNames = ctx.getBeanDefinitionNames();
		//获取指定注解类
//		String[] beanNames =  ctx.getBeanNamesForAnnotation(Service.class);
//		System.out.println("beanNames个数："+beanNames.length);
//		for(String name:beanNames){
//			System.out.println(name);
//		}
	}

	/**
	 * Spring采用的默认区域解析器是AcceptHeaderLocaleResolver
	 * 配置会话区域解析器之SessionLocaleResolver
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		//设置默认区域,
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	}

}
