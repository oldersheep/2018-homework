package com.xxx.mybatis.mapper;

import com.xxx.mybatis.domain.Blog;
import com.xxx.mybatis.domain.BlogExample;
import com.xxx.mybatis.domain.associate.AuthorAndBlog;
import com.xxx.mybatis.domain.associate.BlogAndAuthor;
import com.xxx.mybatis.domain.associate.BlogAndComment;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BlogMapper {
    /**
     * 根据主键查询文章
     * @param bid
     * @return
     */
    Blog selectBlogById(Integer bid);

    /**
     * 根据实体类查询文章
     * @param blog
     * @return
     */
    List<Blog> selectBlogByBean(Blog blog);

    /**
     * 文章列表翻页查询
     * @param rowBounds
     * @return
     */
    List<Blog> selectBlogList(RowBounds rowBounds);

    /**
     * 更新博客
     * @param blog
     * @return
     */
    int updateByPrimaryKey(Blog blog);

    /**
     * 新增博客
     * @param blog
     * @return
     */
    int insertBlog(Blog blog);

    /**
     * 根据博客查询作者，一对一，嵌套结果
     * @param bid
     * @return
     */
    BlogAndAuthor selectBlogWithAuthorResult(Integer bid);

    /**
     * 根据博客查询作者，一对一，嵌套查询，存在N+1问题
     * @param bid
     * @return
     */
    BlogAndAuthor selectBlogWithAuthorQuery(Integer bid);

    /**
     * 查询文章带出文章所有评论（一对多）
     * @param bid
     * @return
     */
    BlogAndComment selectBlogWithCommentById(Integer bid);

    /**
     * 查询作者带出博客和评论（多对多）
     * @return
     */
    List<AuthorAndBlog> selectAuthorWithBlog();

    List<Blog> selectByExample(BlogExample example);

}
