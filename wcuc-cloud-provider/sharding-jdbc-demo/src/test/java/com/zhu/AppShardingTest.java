package com.zhu;

import com.zhu.User.entity.User;
import com.zhu.User.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppShardingTest {


    @Autowired
    private UserMapper userMapper;



    @Test
    public void test(){
        User test1 = new User().setUsername("test1")
                .setAge(20).setCourseId(123123l);
        userMapper.insert(test1);

    }


    @Test
    public void testSelect(){

        User user = userMapper.selectById(1278611075897131010l);
        System.out.println(user);
    }


}
