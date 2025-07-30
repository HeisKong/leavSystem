package com.example.leavSystem.Service;

import com.example.leavSystem.Entity.LeaveRequestEntity;
import com.example.leavSystem.Model.LeaveBalanceModel;
import com.example.leavSystem.Repository.LeaveBalanceRepository;
import com.example.leavSystem.Repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.leavSystem.Entity.LeaveBalanceEntity;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveBalanceService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;


    public LeaveBalanceModel getLeaveBalances() {
        List<LeaveBalanceEntity> balanceEntities = leaveBalanceRepository.findAll();

        int totalDays = 0;
        int year = LocalDate.now().getYear();

        for (LeaveBalanceEntity entity : balanceEntities) {
            totalDays += entity.getRemainingDays();
        }

        LeaveBalanceModel model = new LeaveBalanceModel();
        model.setYear(year);
        model.setRemainingDays(totalDays);

        return model;
    }




}
