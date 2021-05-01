package am.basic.springdemo.commons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class GenericSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = 1900581010229669687L;

    private List<SearchCriteria> list;

    public GenericSpecification(List<SearchCriteria> list,Class<T> type) {
        this.list = list;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : list) {

            switch (criteria.getOperation()) {
                case MORE:
                    predicates.add(builder.greaterThan(root.get(criteria.getName()),criteria.getValue().toString()));
                    break;
                case LESS:
                    predicates.add(builder.lessThan(root.get(criteria.getName()), criteria.getValue().toString()));
                    break;
                case EQUALS:
                    predicates.add(builder.equal(getPath(root,criteria.getName()), criteria.getValue()));
                    break;
                case LIKE:
                    predicates.add(builder.like(builder.lower(root.get(criteria.getName())), "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    break;
                case IN:
                    predicates.add(builder.in(root.get(criteria.getName())).value(criteria.getValue()));
                    break;
                case BETWEEN:
                    List<Long> value = (List<Long>) criteria.getValue();
                    predicates.add(builder.between(root.get(criteria.getName()),value.get(0),value.get(1)));
                    break;
             }

        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }


    private Path<T> getPath(Root<T> root, String name){
        String [] paths = name.split("\\.");
        Path<T> result = root;
        for (String path : paths){
           result = result.get(path);
        }
        return result;
    }
}
