package com.ppikarin;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "privileges", path = "privileges")
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {

    Optional<Privilege> findByName(String name);
}
