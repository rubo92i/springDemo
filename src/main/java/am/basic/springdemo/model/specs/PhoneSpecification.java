package am.basic.springdemo.model.specs;

import am.basic.springdemo.model.Phone;
import am.basic.springdemo.model.dto.PhoneSearchDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
public class PhoneSpecification implements Specification<Phone> {


    private final PhoneSearchDto PhoneSearchDto;


    @Override
    public Predicate toPredicate(Root<Phone> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();


        if (PhoneSearchDto.getModel() != null && !PhoneSearchDto.getModel().isEmpty()) {
            predicates.add(builder.equal(root.get("model"), PhoneSearchDto.getModel()));
        }

        if (PhoneSearchDto.getVendor() != null && !PhoneSearchDto.getVendor().isEmpty()){
            predicates.add(builder.equal(root.get("vendor"), PhoneSearchDto.getVendor()));
        }

        if (PhoneSearchDto.getNumber() != null && !PhoneSearchDto.getNumber().isEmpty()){
            predicates.add(builder.equal(root.get("number"), PhoneSearchDto.getNumber()));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
