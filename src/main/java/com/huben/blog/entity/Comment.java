package com.huben.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author koishi
 */
@Data
@Entity
@Table(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String email;
    private String content;
    private String avatar;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private Blog blog;
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyCommentList = new ArrayList<>();
    @ManyToOne
    private Comment parentComment;

    private boolean adminComment;
}
