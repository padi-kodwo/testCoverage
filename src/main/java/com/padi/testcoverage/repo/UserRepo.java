package com.padi.testcoverage.repo;

import com.padi.testcoverage.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, String>, CrudRepository<User, String>, JpaSpecificationExecutor<User> {
}
