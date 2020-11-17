package com.example.springboot.blog.service.impl;

import com.example.springboot.blog.entity.BlogType;
import com.example.springboot.blog.mapper.BlogTypeMapper;
import com.example.springboot.blog.service.IBlogTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客类型表 服务实现类
 * </p>
 *
 * @author xubo
 * @since 2020-11-17
 */
@Service
public class BlogTypeServiceImpl extends ServiceImpl<BlogTypeMapper, BlogType> implements IBlogTypeService {

}
