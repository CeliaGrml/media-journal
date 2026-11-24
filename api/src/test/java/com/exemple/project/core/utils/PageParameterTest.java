package com.exemple.project.core.utils;

import com.exemple.project.core.exceptions.IllegalParameterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PageParameterTest {
    @Nested
    @DisplayName("toPageRequest")
    class PageRequestConstructor {
        @Test
        void find_page_with_wrong_limit() {
            assertThatThrownBy(() -> new PageParameter(null, 0))
                    .isInstanceOf(IllegalParameterException.class);
        }

        @Test
        void find_page_with_wrong_limit2() {
            assertThatThrownBy(() -> new PageParameter(null, 101))
                    .isInstanceOf(IllegalParameterException.class);
        }

        @Test
        void find_page_with_wrong_offset() {
            assertThatThrownBy(() -> new PageParameter(-1, null))
                    .isInstanceOf(IllegalParameterException.class);
        }
    }

    @Nested
    @DisplayName("toPageRequest")
    class toPageRequest {
        @Test
        void to_page_request_nominal() {
            PageParameter pageParameter = new PageParameter(3, 9);

            assertThat(pageParameter.toPageRequest())
                    .isEqualTo(PageRequest.of(3, 9));
        }

        @Test
        void to_page_request_null_page_number() {
            PageParameter pageParameter = new PageParameter(null, 1);

            assertThatThrownBy(pageParameter::toPageRequest)
                    .isInstanceOf(IllegalParameterException.class);
        }

        @Test
        void to_page_request_null_page_size() {
            PageParameter pageParameter = new PageParameter(1, null);

            assertThatThrownBy(pageParameter::toPageRequest)
                    .isInstanceOf(IllegalParameterException.class);
        }

        @Test
        void to_page_request_with_sort() {
            PageParameter pageParameter = new PageParameter(1, 1, Sort.unsorted());

            assertThat(pageParameter.toPageRequest())
                    .isEqualTo(PageRequest.of(1, 1, Sort.unsorted()));
        }
    }
}