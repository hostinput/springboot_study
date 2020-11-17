package com.example.springboot.blog.service.impl;

import com.example.springboot.blog.entity.Blog;
import com.example.springboot.blog.mapper.BlogMapper;
import com.example.springboot.blog.service.IBlogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客内容表 服务实现类
 * </p>
 *
 * @author xubo
 * @since 2020-11-17
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

}
