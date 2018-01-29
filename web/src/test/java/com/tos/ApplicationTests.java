package com.tos;

import com.tos.controller.UserController;
import com.tos.dao.RoleRepository;
import com.tos.domain.Role;
import com.tos.web.Content;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;

/**
 * Created by ALIENWARE on 2017/3/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    private MockMvc mvc;

   /* @Autowired
    private UserRepository userRepository;*/

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private RedisTemplate redisTemplate;

//    @Autowired
//    private JavaMailSender mailSender;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
        //  ，清空user表
//        userSerivce.deleteAllUsers();
       /* User u1 = userRepository.findByUsername("AAA");

        if(null==u1){
            userRepository.save(new User("AAA","123",new Date()));
        }*/

    }

    /*@Autowired
    private Sender sender;

    @Test
    public void hello() throws Exception {
        sender.send();
    }*/


   /* @Test
    public void findUser(){
        User u1 = userRepository.findByUsername("AAA");
        System.out.println("第一次查询：" + u1.getEmail());
        User u2 = userRepository.findByUsername("AAA");
        System.out.println("第二次查询：" + u2.getEmail());
    }*/

    @Test
    public void testsaveUser(){
        Role role = new Role();
        role.setRole(Content.ROLE_USER);
        roleRepository.save(role);

        Role oneByRole = roleRepository.findOneByRole(Content.ROLE_USER);
        System.out.println(oneByRole);
    }


//    @Test
//    public void test1() throws Exception {
//        stringRedisTemplate.opsForValue().set("aaa", "111");
//        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
//    }



    /*@Test
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("136248471@qq.com");
        message.setTo("136248471@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
    }*/

    /*@Test
    public void sendAttachmentsMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("136248471@qq.com");
        helper.setTo("617638699@qq.com");
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");
        FileSystemResource file = new FileSystemResource(new File("G:\\images\\153_170320163303_2_lit.jpg"));
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file);
        mailSender.send(mimeMessage);
    }*/

    /*@Test
    public void sendInlineMail() throws Exception {
        *//**
         * 这里需要注意的是addInline函数中资源名称weixin需要与正文中cid:weixin对应起来
         *//*
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("136248471@qq.com");
        helper.setTo("136248471@qq.com");
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><img src=\"cid:153_170320163303_2_lit\" ></body></html>", true);
        FileSystemResource file = new FileSystemResource(new File("G:\\images\\153_170320163303_2_lit.jpg"));
        helper.addInline("153_170320163303_2_lit", file);
        mailSender.send(mimeMessage);
    }*/

}
