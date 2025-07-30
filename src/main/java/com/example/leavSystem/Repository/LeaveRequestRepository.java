package com.example.leavSystem.Repository;

import com.example.leavSystem.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.leavSystem.Entity.LeaveRequestEntity;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequestEntity, Integer> {
    List<LeaveRequestEntity> findByUserId(int userId);

    List<LeaveRequestEntity> findByStatus(String status);
}
