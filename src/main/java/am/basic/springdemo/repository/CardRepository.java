package am.basic.springdemo.repository;

import am.basic.springdemo.model.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>, JpaSpecificationExecutor<Card> {


    Page<Card> findAllByUserId(long userId, Pageable pageable);

    boolean existsByNumber(String number);

    boolean existsByNumberAndIdIsNot(String number, long userId);

    Optional<Card> findByIdAndUserId(long id, long userId);

    @Modifying
    @Transactional
    void deleteByIdAndUserId(long id, long userId);

}
