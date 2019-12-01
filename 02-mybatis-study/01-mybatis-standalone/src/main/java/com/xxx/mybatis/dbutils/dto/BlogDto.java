package com.xxx.mybatis.dbutils.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BlogDto implements Serializable {

    private Integer bid;
    private String name;
    private Integer authorId;

}
