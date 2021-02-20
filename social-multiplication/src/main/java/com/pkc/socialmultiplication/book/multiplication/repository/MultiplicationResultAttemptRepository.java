package com.pkc.socialmultiplication.book.multiplication.repository;

import com.pkc.socialmultiplication.book.multiplication.domain.MultiplicationResultAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt,
        Long> {
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);

}
