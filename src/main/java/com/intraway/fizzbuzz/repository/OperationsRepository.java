package com.intraway.fizzbuzz.repository;

import com.intraway.fizzbuzz.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * spring data repository for the operations
 */
public interface OperationsRepository extends JpaRepository<Operation, Long> {

}
