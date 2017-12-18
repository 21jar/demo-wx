package com.demo.domain.wx;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HdAutoReplyRule implements Serializable {

    private Integer id;

    private String replyWord;

    private String replyType;

    private String title;

    private String description;

    private String content;

    private Integer articleCount;

    private String articles;

    private Date createTime;

    private Date updateTime;
}