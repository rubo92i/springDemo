package am.basic.springdemo.model.specs;

import am.basic.springdemo.model.Card;
import am.basic.springdemo.model.dto.CardSearchDto;
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
public class CardSpecification implements Specification<Card> {


    private final CardSearchDto cardSearchDto;


    @Override
    public Predicate toPredicate(Root<Card> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();


        if (cardSearchDto.getBank() != null && !cardSearchDto.getBank().isEmpty()) {
            predicates.add(builder.equal(root.get("bank"), cardSearchDto.getBank()));
        }

        if (cardSearchDto.getCardHolder() != null && !cardSearchDto.getCardHolder().isEmpty()){
            predicates.add(builder.equal(root.get("cardHolder"), cardSearchDto.getCardHolder()));
        }

        if (cardSearchDto.getNumber() != null && !cardSearchDto.getNumber().isEmpty()){
            predicates.add(builder.equal(root.get("number"), cardSearchDto.getNumber()));
        }

        if (cardSearchDto.getCvc() != null && !cardSearchDto.getCvc().isEmpty()){
            predicates.add(builder.equal(root.get("cvc"), cardSearchDto.getCvc()));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
