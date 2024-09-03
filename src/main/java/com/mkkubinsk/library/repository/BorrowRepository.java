package com.mkkubinsk.library.repository;

import com.mkkubinsk.library.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
}
