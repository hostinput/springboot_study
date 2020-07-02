package com.example.springboot.system.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xubo
 * @since 2020-07-02
 */
@TableName("operational_log")
public class OperationalLog extends Model<OperationalLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 操作类型
     */
    private String type;

    /**
     * 详细描述
     */
    private String descripe;

    /**
     * 操作人Id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 操作人名字
     */
    private String username;

    /**
     * 传入参数
     */
    private String params;

    /**
     * 访问路径
     */
    private String url;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getDescripe() {
        return descripe;
    }

    public void setDescripe(String descripe) {
        this.descripe = descripe;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OperationalLog{" +
        "id=" + id +
        ", type=" + type +
        ", descripe=" + descripe +
        ", userId=" + userId +
        ", username=" + username +
        ", params=" + params +
        ", url=" + url +
        ", createTime=" + createTime +
        "}";
    }
}
