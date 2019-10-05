package com.huben.blog.service;

import com.huben.blog.dao.CommentRepository;
import com.huben.blog.entity.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author koishi
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = new Sort(Sort.Direction.ASC, "CreateTime");
        List<Comment> commentList = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
        return eachComment(commentList);
    }

    @Transactional
    @Override
    public Comment save(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        } else {
            comment.setParentComment(null);
        }
        String content = comment.getContent();
        if (content.startsWith("@")) {
            comment.setContent(content.split(" ")[1]);
        }
        comment.setCreateTime(new Date());

        return commentRepository.save(comment);
    }

    private List<Comment> eachComment(List<Comment> commentList) {
        List<Comment> commentListView = new ArrayList<>();
        for (Comment comment : commentList) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentListView.add(c);
        }

        combineChildren(commentListView);
        return commentListView;
    }

    private void combineChildren(List<Comment> commentList) {
        for (Comment comment : commentList) {
            List<Comment> replyList = comment.getReplyCommentList();
            for (Comment reply : replyList) {
                recursively(reply);
            }

            comment.setReplyCommentList(tempReplyList);
            tempReplyList = new ArrayList<>();
        }
    }

    private List<Comment> tempReplyList = new ArrayList<>();

    private void recursively(Comment comment) {
        tempReplyList.add(comment);
        if (comment.getReplyCommentList().size() > 0) {
            List<Comment> replyList = comment.getReplyCommentList();
            for (Comment reply : replyList) {
                tempReplyList.add(reply);
                if (reply.getReplyCommentList().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }
}
