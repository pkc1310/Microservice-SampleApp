package com.pkc.socialmultiplication.book.multiplication.repository;

import com.pkc.socialmultiplication.book.multiplication.domain.Multiplication;
import org.springframework.data.repository.CrudRepository;

public interface MultiplicationRepository extends
        CrudRepository<Multiplication, Long> {
}
