package com.example.springboot.blog.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客类型表
 * </p>
 *
 * @author xubo
 * @since 2020-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blog_type")
public class BlogType extends Model<BlogType> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 博客类型名称
     */
    @TableField("type_name")
    private String typeName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
