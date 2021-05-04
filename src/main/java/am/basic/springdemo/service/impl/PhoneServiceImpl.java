package am.basic.springdemo.service.impl;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Phone;
import am.basic.springdemo.model.dto.PhoneSearchDto;
import am.basic.springdemo.model.specs.PhoneSpecification;
import am.basic.springdemo.repository.PhoneRepository;
import am.basic.springdemo.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository PhoneRepository;

    @Override
    public Phone add(Phone Phone) throws ResponseException {
        ResponseException.check(PhoneRepository.existsByNumber(
                Phone.getNumber()),
                HttpStatus.CONFLICT,
                "Phone with such number already exists");
        return PhoneRepository.save(Phone);
    }

    @Override
    @Transactional
    public Phone update(long id, Phone Phone, long userId) throws ResponseException {
        Phone fromDb = PhoneRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Phone with such id is not exists or you have no permission edit on it"));

        ResponseException.check(
                PhoneRepository.existsByNumberAndIdIsNot(Phone.getNumber(), id),
                HttpStatus.CONFLICT,
                "Phone with such number already exists");

        fromDb.setModel(Phone.getModel());
        fromDb.setVendor(Phone.getVendor());
        fromDb.setNumber(Phone.getNumber());

        return fromDb;
    }

    @Override
    public void delete(long id, long userId) {
        PhoneRepository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public Page<Phone> getByUserId(long userId, Pageable pageable) {
        return PhoneRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Page<Phone> search(PhoneSearchDto PhoneSearchDto, Pageable pageable) {
        return PhoneRepository.findAll(new PhoneSpecification(PhoneSearchDto), pageable);
    }
}
