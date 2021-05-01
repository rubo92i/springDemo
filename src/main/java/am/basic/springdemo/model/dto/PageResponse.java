package am.basic.springdemo.model.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageResponse<T> {


    private List<T> content;

    private int pageNumber;

    private int totalPages;

    private long totalElement;

    private boolean last;


    public PageResponse(Page<T> page){
        this.content = page.getContent();
        this.last = page.isLast();
        this.pageNumber = page.getNumber();
        this.totalElement = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }


}
