package org.apathinternational.faithpathrestful.repository;

import org.apathinternational.faithpathrestful.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // You can add custom queries or methods if needed
}