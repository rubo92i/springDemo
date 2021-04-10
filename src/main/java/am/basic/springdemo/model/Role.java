package am.basic.springdemo.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    public Role(String name) {
        this.name = name;
    }


    public Role withName(String name){
        this.name = name;
        return this;
    }


    public Role withId(long id){
        this.id = id;
        return this;
    }
}
