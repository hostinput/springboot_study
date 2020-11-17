package com.example.springboot.blog.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客内容表
 * </p>
 *
 * @author xubo
 * @since 2020-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Blog extends Model<Blog> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 博客类型
     */
    @TableField("blog_type_id")
    private Integer blogTypeId;

    /**
     * 博客类型（冗余）
     */
    @TableField("blog_type_name")
    private String blogTypeName;

    /**
     * 博客存放htm文件路径
     */
    @TableField("blog_url")
    private String blogUrl;

    /**
     * 创建人
     */
    @TableField("create_user")
    private Integer createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField("update_user")
    private String updateUser;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 浏览人数
     */
    @TableField("browser_count")
    private Integer browserCount;

    /**
     * 评论人数
     */
    @TableField("comment_couont")
    private Integer commentCouont;

    /**
     * 删除状态
     */
    @TableField("delete_status")
    private String deleteStatus;

    /**
     * 是否可以被评论
     */
    @TableField("is_can_comment")
    private Integer isCanComment;

    /**
     * 博客标题
     */
    @TableField("blog_title")
    private String blogTitle;

    /**
     * 点赞数
     */
    @TableField("praise_count")
    private String praiseCount;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
