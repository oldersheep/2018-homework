package com.xxx.mybatis.mapper;

import com.xxx.mybatis.domain.Blog;

// 扩展类继承了MBG生成的接口和Statement
public interface BlogMapperExt extends BlogMapper {
    /**
     * 根据名称查询文章
     * @param name
     * @return
     */
    Blog selectBlogByName(String name);
}
