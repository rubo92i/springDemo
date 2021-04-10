package am.basic.springdemo.repository;

import am.basic.springdemo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment,Long> {


    List<Comment> getByUserId(long userId);


    @Modifying
    void deleteByIdAndUserId(long commentId, long userId);

}
