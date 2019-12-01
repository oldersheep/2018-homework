package com.xxx.mybatis.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Author implements Serializable {

    Integer authorId; // 作者ID
    String authorName; // 作者名称

}
