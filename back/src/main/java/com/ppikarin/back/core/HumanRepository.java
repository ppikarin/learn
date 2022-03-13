package com.ppikarin.back.core;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HumanRepository extends CrudRepository<Human, Long> {

    Optional<Human> findByName(String name);
}