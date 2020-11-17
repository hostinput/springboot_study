-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
  `id`              int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username`        varchar(50) NOT NULL COMMENT '用户名',
  `password`        varchar(50) NOT NULL COMMENT '密码',
  `salt`            varchar(128) DEFAULT NULL COMMENT '加密盐值',
  `email`           varchar(50)  DEFAULT NULL COMMENT '邮箱',
  `phone`           varchar(50)  DEFAULT NULL COMMENT '联系方式',
  `sex`             int(1) DEFAULT NULL COMMENT '性别：1男2女',
  `age`             int(3) DEFAULT NULL COMMENT '年龄',
  `status`          int(1) NOT NULL COMMENT '用户状态：1有效; 2删除',
  `create_time`     datetime     DEFAULT NULL COMMENT '创建时间',
  `update_time`     datetime     DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime     DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `a` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
  `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name`        varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `status`      int(1) NOT NULL COMMENT '状态：1有效；2删除',
  `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
  `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name`        varchar(100) NOT NULL COMMENT '权限名称',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `url`         varchar(255) DEFAULT NULL COMMENT '权限访问路径',
  `perms`       varchar(255) DEFAULT NULL COMMENT '权限标识',
  `parent_id`   int(11) DEFAULT NULL COMMENT '父级权限id',
  `type`        int(1) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `order_num`   int(3) DEFAULT '0' COMMENT '排序',
  `icon`        varchar(50)  DEFAULT NULL COMMENT '图标',
  `status`      int(1) NOT NULL COMMENT '状态：1有效；2删除',
  `create_time` datetime     DEFAULT NULL,
  `update_time` datetime     DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
  `id`            int(11) NOT NULL AUTO_INCREMENT,
  `role_id`       varchar(20) NOT NULL COMMENT '角色id',
  `permission_id` varchar(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=869 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
  `id`      int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for operational_log
-- ----------------------------
DROP TABLE IF EXISTS `operational_log`;
CREATE TABLE `operational_log`
(
  `id`          int(11) NOT NULL AUTO_INCREMENT,
  `type`        varchar(20) NOT NULL COMMENT '操作类型',
  `descripe`    varchar(255) DEFAULT NULL COMMENT '详细描述',
  `user_id`     int(11) NOT NULL COMMENT '操作人Id',
  `username`    varchar(50) NOT NULL COMMENT '操作人名字',
  `params`      varchar(50)  DEFAULT NULL COMMENT '传入参数',
  `url`         varchar(50) NOT NULL COMMENT '访问路径',
  `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
-- ---------------------------
-- 博客类型
-- ---------------------------
CREATE TABLE `blog_type`
(
  `id`        INT         NOT NULL,
  `type_name` VARCHAR(50) NOT NULL COMMENT '博客类型名称',
  PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHAR
  SET = utf8 COMMENT = '博客类型表';
-- ---------------------------
-- 博客
-- ---------------------------
CREATE TABLE `blog`
(
  `id`             int          NOT NULL,
  `blog_type_id`   int          NOT NULL COMMENT '博客类型',
  `blog_type_name` varchar(50)  NOT NULL COMMENT '博客类型（冗余）',
  `blog_url`       varchar(255) NOT NULL COMMENT '博客存放htm文件路径',
  `create_user`    int          NOT NULL COMMENT '创建人',
  `create_time`    datetime     NOT NULL COMMENT '创建时间',
  `update_user`    varchar(50) NULL COMMENT '修改人',
  `update_time`    datetime NULL COMMENT '修改时间',
  `browser_count`  int NULL COMMENT '浏览人数',
  `comment_couont` int NULL COMMENT '评论人数',
  `delete_status`  varchar(255) NOT NULL COMMENT '删除状态',
  `is_can_comment` tinyint      NOT NULL COMMENT '是否可以被评论',
  `blog_title`     varchar(255) NOT NULL COMMENT '博客标题',
  `praise_count`   varchar(255) NULL COMMENT '点赞数',
  PRIMARY KEY (`id`)
)
  ENGINE=InnoDB
  DEFAULT CHAR SET =utf8 COMMENT='博客内容表';
-- ---------------------------
-- 博客评论表
-- ---------------------------

CREATE TABLE `blog_comment`
(
  `id`                int NOT NULL,
  `blog_id`           int NOT NULL COMMENT '博客Id',
  `comment_text`      varchar(1000) NULL COMMENT '评论文字内容',
  `comment_img`       varchar(255) NULL COMMENT '评论图片Url',
  `commnet_parent_id` int NOT NULL COMMENT '评论父类id',
  PRIMARY KEY (`id`)
)
  ENGINE=InnoDB
  DEFAULT CHAR SET =utf8 COMMENT='评论表'
;



