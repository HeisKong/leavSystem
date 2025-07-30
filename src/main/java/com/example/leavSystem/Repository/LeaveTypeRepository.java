package com.example.leavSystem.Repository;

import com.example.leavSystem.Entity.LeaveTypeEntity;

import com.example.leavSystem.Entity.LeaveTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveTypeEntity, Integer> {
}
