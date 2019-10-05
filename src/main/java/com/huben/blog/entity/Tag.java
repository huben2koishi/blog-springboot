package com.huben.blog.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author koishi
 */
@Data
@Entity
@Table(name = "t_tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "标签名称不能为空")
    private String name;

    @ManyToMany(mappedBy = "tagList")
    private List<Blog> blogList = new ArrayList<>();
}
