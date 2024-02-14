package org.apathinternational.faithpathrestful.repository;

import org.apathinternational.faithpathrestful.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Administrator, Long> {
}