package am.basic.springdemo.service;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Phone;
import am.basic.springdemo.model.dto.PhoneSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneService {

    Phone add(Phone Phone) throws ResponseException;

    Phone update(long id, Phone Phone, long userId) throws ResponseException;

    void delete(long id, long userId);

    Page<Phone> getByUserId(long userId, Pageable pageable);


    Page<Phone> search(PhoneSearchDto phoneSearchDto, Pageable pageable);
}
