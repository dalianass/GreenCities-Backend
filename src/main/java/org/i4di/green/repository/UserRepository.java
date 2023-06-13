package org.i4di.green.repository;

import org.i4di.green.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAllByOrderByFirstNameAsc();

    Optional<User> findById(Long id);

    List<User> findByRole(String role);

    Optional<User> findByEmail(String email);

    List<User> findAllByFirstNameOrLastName(String firstName, String lastName);


    @Query(value = "UPDATE user SET deleted = TRUE WHERE id = :id ;", nativeQuery = true)
    @Modifying
    void delete(@Param(value = "id") Long id);
}
