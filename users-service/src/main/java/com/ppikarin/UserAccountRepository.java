package com.ppikarin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "userAccounts", path = "userAccounts")
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    List<UserAccount> findByLastName(String lastName);

    Optional<UserAccount> findByEmail(String email);
}
