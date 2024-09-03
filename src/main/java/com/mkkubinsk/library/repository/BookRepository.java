package com.mkkubinsk.library.repository;

import com.mkkubinsk.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
