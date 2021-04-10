package am.basic.springdemo.service.impl;

import am.basic.springdemo.model.Comment;
import am.basic.springdemo.repository.CommentRepository;
import am.basic.springdemo.service.CommentService;
import lombok.Data;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepository;

    @Override
    public List<Comment> getByUserId(long id) {
        return commentRepository.getByUserId(id);
    }

    @Override
    public void add(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    @Async("taskExecutor2")
    public void deleteByUser(long commentId, long userId) {
        commentRepository.deleteByIdAndUserId(commentId,userId);
    }
}
