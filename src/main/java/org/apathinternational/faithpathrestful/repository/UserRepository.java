package org.apathinternational.faithpathrestful.repository;

import org.apathinternational.faithpathrestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom queries or methods if needed
    Optional<User> findByUsername(String username);
}