package com.example.springboot.blog.service.impl;

import com.example.springboot.blog.entity.BlogComment;
import com.example.springboot.blog.mapper.BlogCommentMapper;
import com.example.springboot.blog.service.IBlogCommentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author xubo
 * @since 2020-11-17
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements IBlogCommentService {

}
