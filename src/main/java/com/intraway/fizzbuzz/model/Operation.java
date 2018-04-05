package com.intraway.fizzbuzz.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "operation_id")
    private Long id;

    @NotNull
    private int min;

    @NotNull
    private int max;

    @NotNull
    private String timestamp;

    public Operation() {
    }

    public Operation(@NotNull int min, @NotNull int max, @NotNull String timestamp) {
        this.min = min;
        this.max = max;
        this.timestamp = timestamp;
    }

}
