package com.simple.mybatispluslearn.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


/**
 * @author Simple
 * @data 2021/3/25 11:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    //对应数据库的主键（uuid、自增id、雪花算法、redis、zookeeper）
    @TableId(type = IdType.AUTO)    //INPUT是默认方案
    private Long id;
    private String name;
    private Integer age;
    private String email;


    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version //乐观锁
    private Integer version;

    @TableLogic //逻辑删除
    private Integer deleted;
}
