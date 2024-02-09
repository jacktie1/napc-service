package org.apathinternational.faithpathrestful.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.apathinternational.faithpathrestful.entity.Management;

@Repository
public interface ManagementRepository extends JpaRepository<Management, Long> {
}
