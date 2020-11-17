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
 * 评论表
 * </p>
 *
 * @author xubo
 * @since 2020-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blog_comment")
public class BlogComment extends Model<BlogComment> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 博客Id
     */
    @TableField("blog_id")
    private Integer blogId;

    /**
     * 评论文字内容
     */
    @TableField("comment_text")
    private String commentText;

    /**
     * 评论图片Url
     */
    @TableField("comment_img")
    private String commentImg;

    /**
     * 评论父类id
     */
    @TableField("commnet_parent_id")
    private Integer commnetParentId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
