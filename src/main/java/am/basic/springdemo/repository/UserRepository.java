package am.basic.springdemo.repository;

import am.basic.springdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


//    @Query(value = "SELECT u FROM  User u where u.username = :username AND u.card.number = :number")
//    User getByUsernameJPQL(@Param("username") String s, @Param("number") String number);


    @Query(nativeQuery = true, value = "SELECT * FROM  user where username = :username ")
    User getByUsernameN(@Param("username") String s);


    boolean existsByUsername(String username);


    Optional<User> getByUsername(String username);

    User getByIdAndCodeIsNotAndUsernameIsEndingWithAndPasswordIsLike(long id, String code,String username,String password);

    User getByUsernameAndCodeIn(String username,List<String> codes);

 //   Optional<User> getByUsername(String username);



}
