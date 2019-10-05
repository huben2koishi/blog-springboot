package com.huben.blog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author koishi
 */
@Setter
@Getter
@ToString
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;
}
