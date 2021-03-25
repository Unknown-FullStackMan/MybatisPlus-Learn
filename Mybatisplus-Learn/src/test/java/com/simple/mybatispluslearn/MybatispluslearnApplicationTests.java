package com.simple.mybatispluslearn;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.simple.mybatispluslearn.mapper.UserMapper;
import com.simple.mybatispluslearn.pojo.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
class MybatispluslearnApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    void contextLoads() {
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));

        // 查询全部用户
        // selectList(参数) 这里的参数是一个Wrapper，条件构造器，这里我们先不用，写个null占着

        List<User> userList = userMapper.selectList(null);
       // Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    // 测试插入
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("Simple6");
        user.setAge(22);
        user.setEmail("1372713212@qq.com");
        int result = userMapper.insert(user);  // 帮我们自动生成id
        System.out.println(result); // 受影响的行数
        System.out.println(user); // 发现，id自动回填
    }
    // 测试更新
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1375051972579049486L);
        user.setName("Simple牛逼牛逼");
        user.setAge(12);
        // 注意： updateById 但是参数是一个对象
        int i = userMapper.updateById(user);
        User user1 = userMapper.selectById(5L);
        System.out.println(user1);
        System.out.println("受影响的行数"+i);
    }


    // 测试乐观锁单线程成功
    @Test
    public void testOptimisticLock(){
        // 1、查询用户信息
        User user = userMapper.selectById(1);
        // 2、修改用户信息
        user.setName("simple");
        user.setEmail("123123132@qq.com");
        // 3、执行更新操作
        userMapper.updateById(user);
    }

    // 测试乐观锁多线程失败  多线程
 /*
 线程1 虽然执行了赋值语句，但是还没进行更新操作，线程2就插队了抢先更新了，
 由于并发下，可能导致线程1执行不成功
 如果没有乐观锁就会覆盖线程2的值
 */
    @Test
    public void testOptimisticLock2(){
        // 线程1
        User user = userMapper.selectById(1);
        user.setName("simpleniubi");
        user.setEmail("123123132@qq.com");

        // 模拟另外一个线程执行了插队操作
        // 线程2
        User user2 = userMapper.selectById(1);
        user2.setName("simpleNIUbi");
        user2.setEmail("123123132@qq.com");
        userMapper.updateById(user2);

        // 自旋锁来多次尝试提交
        userMapper.updateById(user);   //如果没有乐观锁就会覆盖插队线程的值
    }

    // 测试查询
    @Test
    public void testSelectById(){
        // 查询一个
        User user = userMapper.selectById(1);
        System.out.println(user);
        // 查询批量查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    // 条件查询 map
    @Test
    public void testSelectByBatchIds(){
        HashMap<String, Object> map = new HashMap<>();
        // 自定义要查询的条件
        map.put("name","Tom");
        map.put("age","28");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void testPage(){
        // 参数1 当前页 ；参数2 页面大小
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page,null);

        page.getRecords().forEach(System.out::println);
        System.out.println("getCurrent()"+page.getCurrent());
        System.out.println("page.getSize()"+page.getSize());
        System.out.println("page.getTotal()"+page.getTotal());
    }

    // 测试删除
    @Test
    public void testDeleteId(){
        userMapper.deleteById(1367357737224667139L );
    }
    // 批量删除
    @Test
    public void testDeleteBatchId(){
        userMapper.deleteBatchIds(Arrays.asList(1367357737224667142L,1367357737224667143L));
    }
    //通过map删除
    @Test
    public void testDeleteMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id","1367357737224667144");
        userMapper.deleteByMap(map);
    }

    // 测试逻辑删除
    @Test
    public void testDeleteId2(){
        userMapper.deleteById(1375051972579049486L );
    }
}
