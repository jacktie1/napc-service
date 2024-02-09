package org.apathinternational.faithpathrestful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.apathinternational.faithpathrestful.entity.Reference;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
    List<Reference> findByReferenceType(String referenceType);

    @Query("SELECT r FROM Reference r WHERE referenceType IN :referenceTypes")
    List<Reference> findByReferenceTypes(@Param("referenceTypes") List<String> referenceTypes);
}
