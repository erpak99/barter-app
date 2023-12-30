package com.erpak.barter.repository;

import com.erpak.barter.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
}
