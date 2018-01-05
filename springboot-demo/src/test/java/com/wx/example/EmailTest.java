package com.wx.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by wangxiong on 2018/1/5.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     *修改application.properties的用户，才能发送。
     */
//    @Test
    public void sendSimpleEmail(){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("wangxiongxp@163.com");//发送者.
        message.setTo("345293340@qq.com");//接收者.
        message.setSubject("测试邮件（邮件主题）");//邮件主题.
        message.setText("这是邮件内容");//邮件内容.

        mailSender.send(message);//发送邮件
    }

    /**
     * 邮件中使用静态资源
     * @throws MessagingException
     */
//    @Test
    public void sendAttachmentsEmail() throws MessagingException{

        //这个是javax.mail.internet.MimeMessage下的，不要搞错了。
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        //基本设置.
        helper.setFrom("wangxiongxp@163.com");//发送者.
        helper.setTo("345293340@qq.com");//接收者.
        helper.setSubject("测试附件（邮件主题）");//邮件主题.
        helper.setText("这是邮件内容（有附件哦.）");//邮件内容.

        //org.springframework.core.io.FileSystemResource下的:
        //附件1,获取文件对象.
        FileSystemResource file1 = new FileSystemResource(new File("/Users/wangxiong/Pictures/1.jpg"));
        //添加附件，这里第一个参数是在邮件中显示的名称，也可以直接是head.jpg，但是一定要有文件后缀，不然就无法显示图片了。
        helper.addAttachment("头像1.jpg",file1);

        //附件2
        FileSystemResource file2 = new FileSystemResource(new File("/Users/wangxiong/Pictures/4.jpg"));
        helper.addAttachment("头像2.jpg",file2);

        mailSender.send(mimeMessage);
    }

    /**
     *模板邮件；
     *@throws Exception
     */
    @Test
    public void sendTemplateMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        //基本设置.
        helper.setFrom("wangxiongxp@163.com");//发送者.
        helper.setTo("345293340@qq.com");//接收者.
        helper.setSubject("模板邮件（邮件主题）");//邮件主题.

        Map<String, Object> model = new HashMap<>();
        model.put("username", "林峰");

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

        // 设定去哪里读取相应的ftl模板
        cfg.setClassForTemplateLoading(this.getClass(),"/templates");

        // 在模板文件目录中寻找名称为name的模板文件
        Template template   = cfg.getTemplate("email.ftl");

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
        helper.setText(html, true);

        mailSender.send(mimeMessage);
    }

}
