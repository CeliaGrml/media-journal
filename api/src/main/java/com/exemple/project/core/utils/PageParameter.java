package com.exemple.project.core.utils;

import com.exemple.project.core.exceptions.IllegalParameterException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageParameter {
    private Integer pageNumber;
    private Integer pageSize;
    private Sort sort;

    public PageParameter() {
    }

    public PageParameter(Integer pageNumber, Integer pageSize) {
        this(pageNumber, pageSize, null);
    }

    public PageParameter(Integer pageNumber, Integer pageSize, Sort sort) {
        if (pageNumber != null && pageNumber < 0) {
            throw new IllegalParameterException("PageNumber should be positive");
        }
        if (pageSize != null && (pageSize <= 0 || pageSize > 100)) {
            throw new IllegalParameterException("Page size should be superior to zero and inferior or equals to 100");
        }
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    PageParameter applyDefault(int defaultPageNumber, int defaultPageSize) {
        return new PageParameter(pageNumber != null ? pageNumber : defaultPageNumber,
                pageSize != null ? pageSize : defaultPageSize);
    }

    PageRequest toPageRequest() {
        if (pageNumber == null || pageSize == null) {
            throw new IllegalParameterException("Page number and page size should not be null");
        }
        if (sort == null) {
            return PageRequest.of(pageNumber, pageSize);
        } else {
            return PageRequest.of(pageNumber, pageSize, sort);
        }
    }

    //Necessary to use @ModelAttribute on PageParameter
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
