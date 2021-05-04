package am.basic.springdemo.repository;

import am.basic.springdemo.model.Phone;
import am.basic.springdemo.model.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>, JpaSpecificationExecutor<Phone> {


    Page<Phone> findAllByUserId(long userId, Pageable pageable);

    boolean existsByNumber(String number);

    boolean existsByNumberAndIdIsNot(String number, long userId);

    Optional<Phone> findByIdAndUserId(long id, long userId);

    @Modifying
    @Transactional
    void deleteByIdAndUserId(long id, long userId);

}
