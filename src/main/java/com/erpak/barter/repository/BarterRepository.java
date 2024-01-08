package com.erpak.barter.repository;

import com.erpak.barter.enums.BarterStatus;
import com.erpak.barter.model.Barter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarterRepository extends JpaRepository<Barter, Integer> {

    List<Barter> findByUserOneIdOrUserTwoId(int userOneId, int userTwoId);

    List<Barter> findByUserOneIdAndBarterStatusOrUserTwoIdAndBarterStatus(
            int userOneId, BarterStatus status1,
            int userTwoId, BarterStatus status2);

}
