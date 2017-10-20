/**
 * 
 */
package com.stashinvest.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stashinvest.userservice.model.User;

/**
 * This class contains all queries.
 * 
 * @author abhimanyu
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u where u.fullName = :metadata or u.email = :metadata or u.metadata like CONCAT('%',:metadata,'%')")
	List<User> findByMetadata(@Param("metadata") final String metadata);
	
	@Query("select u from User u where u.accountKey is null")
	List<User> findByEmptyKey();

}
