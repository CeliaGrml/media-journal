package com.exemple.project.core.utils;

import org.springframework.data.domain.PageRequest;

public interface PageService {
    int DEFAULT_PAGE_SIZE = 50;
    int DEFAULT_PAGE_NUMBER = 0;

    default int getDefaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    default PageParameter applyDefault(PageParameter pageParameter) {
        return pageParameter != null
                ? pageParameter.applyDefault(DEFAULT_PAGE_NUMBER, getDefaultPageSize())
                : new PageParameter(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    }

    default PageRequest pageRequest(PageParameter pageParameter) {
        return applyDefault(pageParameter).toPageRequest();
    }
}
