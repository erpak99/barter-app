package com.erpak.barter.repository;

import com.erpak.barter.model.Barter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarterRepository extends JpaRepository<Barter, Integer> {
}
