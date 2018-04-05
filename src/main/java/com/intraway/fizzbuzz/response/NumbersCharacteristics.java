package com.intraway.fizzbuzz.response;

import lombok.Data;

@Data
public class NumbersCharacteristics {

    private String timestamp;
    private String code;
    private String description;
    private String list;

    public NumbersCharacteristics() {
    }

    public NumbersCharacteristics(String timestamp, String code, String description, String list) {
        this.timestamp = timestamp;
        this.code = code;
        this.description = description;
        this.list = list;
    }
}
