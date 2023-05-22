package com.example.kosproject.repository.spec;

import com.example.kosproject.util.QueryOperator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class SearchCriteria {
    private String key;
    private QueryOperator operator;
    private String value;

    public SearchCriteria() {
    }

    public SearchCriteria(String key, QueryOperator operator, String value) {
        this.key = key;
        this.operator = operator;
        this.value = value;
    }
}
