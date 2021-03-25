package com.simple.mybatispluslearn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.mybatispluslearn.mapper.UserMapper;
import com.simple.mybatispluslearn.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author Simple
 * @data 2021/3/25 22:53
 */
@SpringBootTest
public class WrapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads(){
        // 查询 name 不为空的用户，并且邮箱不为空的用户，年龄大于等于20
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age",20);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void getOneName(){
        // 查询名字 Jack
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","Jack");  // 查询一个数据
        userMapper.selectOne(wrapper);
    }
    @Test
    void betweenTest(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",25 ,100);
        Integer count = userMapper.selectCount(wrapper);// 查询结果数
        System.out.println(count);
    }
    // 模糊查询
    @Test
    void likeTest(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 左和右
        wrapper.notLike("name","imple")
                .likeRight("name","S");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }
    // 通过id进行排序
    @Test
    void orderById(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //
        wrapper.orderByDesc("id");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }


}
