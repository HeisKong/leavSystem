package com.example.leavSystem.Repository;

import com.example.leavSystem.Entity.LeaveBalanceEntity;
import com.example.leavSystem.Entity.LeaveTypeEntity;
import com.example.leavSystem.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalanceEntity, Integer> {
    Optional<LeaveBalanceEntity> findByUserAndLeaveTypeIdAndYear(UserEntity user, LeaveTypeEntity leaveTypeId, int year);

}

