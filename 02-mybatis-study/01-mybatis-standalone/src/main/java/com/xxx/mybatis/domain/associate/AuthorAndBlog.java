package com.xxx.mybatis.domain.associate;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuthorAndBlog implements Serializable {

    Integer authorId; // 作者ID
    String authorName; // 作者名称
    List<BlogAndComment> blog; // 文章和评论列表

}
