package com.huben.blog.service;

import com.huben.blog.entity.Comment;

import java.util.List;

/**
 * @author koishi
 */
public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    Comment save(Comment comment);
}
