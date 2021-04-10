package am.basic.springdemo.service;

import am.basic.springdemo.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getByUserId(long id);

    void add(Comment comment);


    void deleteByUser(long commentId, long userId);

}
