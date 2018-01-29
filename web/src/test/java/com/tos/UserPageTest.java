package com.tos;

import com.tos.dao.UserPageRepository;
import com.tos.domain.UserP;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by qq136 on 2017/10/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPageTest {

    @Autowired
    private UserPageRepository userPageRepository;

    @Test
    public void userPage(){

        //显示第一页每页显示2条
        PageRequest pageRequest = new PageRequest(1,2);

        //根据名称进行查询
        Page<UserP> stus = userPageRepository.findByUsername("AAA",pageRequest);
        Assert.assertEquals(1,stus.getTotalPages());//目标页
        Assert.assertEquals(2,stus.getTotalElements()); //返回实例格式
        Assert.assertEquals(1,stus.getNumber());//总页数
        List<UserP> content = stus.getContent();
        System.out.println(content);

        Assert.assertEquals("111", "111");
    }

    //排序的实现代码
    @Test
    public void testSort() {
        //设置排序方式为name降序
        List<UserP> stus = userPageRepository.findByUsername("AAA"
                ,new Sort(Sort.Direction.ASC,"username"));
        Assert.assertEquals(2,stus.get(0).getId()+0);
/*
        //设置排序以name和address进行升序
        stus = userPageRepository.findByUsername("AAA"
                ,new Sort(Sort.Direction.ASC,"name","address"));
        Assert.assertEquals(8,stus.get(0).getId()+0);

        //设置排序方式以name升序，以address降序
        Sort sort = new Sort(
                new Sort.Order(Sort.Direction.ASC,"name"),
                new Sort.Order(Sort.Direction.DESC,"address"));

        stus = userPageRepository.findByUsername("AAA",sort);
        Assert.assertEquals(7,stus.get(0).getId()+0);*/
    }


}
