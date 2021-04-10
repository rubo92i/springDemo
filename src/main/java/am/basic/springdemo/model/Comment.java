package am.basic.springdemo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String content;

    @Column(name = "user_id")
    private long userId;


}
