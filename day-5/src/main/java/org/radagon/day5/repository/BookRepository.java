package org.radagon.day5.repository;

import org.radagon.day5.entity.Book;
import org.radagon.day5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserId(Long id);
}
