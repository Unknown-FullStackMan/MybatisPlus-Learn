package com.simple.mybatispluslearn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simple.mybatispluslearn.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Simple
 * @data 2021/3/25 11:22
 */

// 在对应的Mapper上面实现基本的接口BaseMapper
@Repository  // 代码持久层
@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 所有的CRUD操作都已经编写完成了
    // 你不需要像以前那样配置一大堆文件了
}