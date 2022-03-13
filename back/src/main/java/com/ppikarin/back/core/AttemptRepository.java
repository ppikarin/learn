package com.ppikarin.back.core;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttemptRepository extends CrudRepository<Attempt, Long> {

    @EntityGraph("eg-attempt-human")
    List<Attempt> findTop10ByHumanNameOrderByIdDesc(String name);

    @Query("SELECT a FROM Attempt a WHERE a.human.name = ?1 ORDER BY a.id DESC")
    List<Attempt> lastAttempts(String name);
}