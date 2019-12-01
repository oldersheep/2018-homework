package com.xxx.mybatis.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Comment implements Serializable {

    Integer commentId; // 评论ID
    Integer bid; // 所属文章ID
    String content; // 内容

}
