package am.basic.springdemo.commons.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class SearchRequest {

    private List<SearchCriteria> criteria;




    public List<SearchCriteria> getCriteria(){
        if (criteria == null){
            criteria = Collections.emptyList();
        }
        return criteria;
    }
}
