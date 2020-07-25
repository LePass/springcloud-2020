package com.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.spring.bean.Person;
import com.spring.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Wangx
 * @create 2020/7/7
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

    @Test
    public void test(){
        User user = new User();
        user.setAge(18);
        user.setName("张三");
        Person<User> person = new Person<>();
        person.setObj(user);
        getPerson(person);
        String str="{\n" +
                "    \"jobIds\":[\n" +
                "        [\n" +
                "            2\n" +
                "        ],\n" +
                "        [\n" +
                "            3\n" +
                "        ]\n" +
                "    ]\n" +
                "}";

        JSONArray objects = JSON.parseArray(str);

    }

    public void getPerson(Person person){
        System.out.println("泛型="+person.toString());
    }
}
