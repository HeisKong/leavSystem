package com.example.leavSystem.Service;

import com.example.leavSystem.Entity.LeaveBalanceEntity;
import com.example.leavSystem.Entity.LeaveRequestEntity;
import com.example.leavSystem.Entity.LeaveTypeEntity;
import com.example.leavSystem.Entity.UserEntity;
import com.example.leavSystem.Model.LeaveRequestModel;
import com.example.leavSystem.Repository.LeaveBalanceRepository;
import com.example.leavSystem.Repository.LeaveRequestRepository;
import com.example.leavSystem.Repository.LeaveTypeRepository;
import com.example.leavSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;
    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;

    public void createLeaveRequest(LeaveRequestModel leaveRequestModel) {
        UserEntity user = userRepository.findById(leaveRequestModel.getUser_id()).orElse(null);

        if (user != null) {
            LeaveTypeEntity leaveTypeId = leaveTypeRepository.findById(leaveRequestModel.getLeave_type_id()).orElse(null);


            LeaveRequestEntity leaveRequestEntity = new LeaveRequestEntity();
            leaveRequestEntity.setUser(user);
            leaveRequestEntity.setLeaveType(leaveTypeId);
            leaveRequestEntity.setStatus(leaveRequestModel.getStatus());
            leaveRequestEntity.setStartDate(leaveRequestModel.getStart_date());
            leaveRequestEntity.setEndDate(leaveRequestModel.getEnd_date());
            leaveRequestEntity.setReason(leaveRequestModel.getReason());
            leaveRequestRepository.save(leaveRequestEntity);

        }
    }



    public List<LeaveRequestModel> getAll() {
        List<LeaveRequestModel> leaveRequestModel = new ArrayList<>();
        List<LeaveRequestEntity> leaveRequestEntity = leaveRequestRepository.findAll();
        for(LeaveRequestEntity entity : leaveRequestEntity){
            LeaveRequestModel response = new LeaveRequestModel();
            response.setUsername(entity.getUser().getUsername());
            response.setDepartment(entity.getUser().getDepartment());
            response.setId(entity.getId());
            response.setUser_id(entity.getUser().getId());
            response.setLeave_type_id(entity.getLeaveType().getId());
            response.setEnd_date(entity.getEndDate());
            response.setStart_date(entity.getStartDate());
            response.setReason(entity.getReason());
            response.setStatus(entity.getStatus());
            leaveRequestModel.add(response);
        }
        return leaveRequestModel;
    }

    public void updateLeaveRequest(int userId, LeaveRequestModel leaveRequestModel) {
        List<LeaveRequestEntity> leaveRequests = leaveRequestRepository.findByUserId(userId);

        LeaveTypeEntity leaveTypeId = leaveTypeRepository.findById(leaveRequestModel.getLeave_type_id()).orElse(null);

        for (LeaveRequestEntity leaveSetUp : leaveRequests) {
            if (leaveSetUp.getStartDate().equals(leaveRequestModel.getStart_date()) &&
                    leaveSetUp.getEndDate().equals(leaveRequestModel.getEnd_date())) {
                leaveSetUp.setStatus(leaveRequestModel.getStatus());
                leaveRequestRepository.save(leaveSetUp);
                break;
            }
        }


        UserEntity user = userRepository.findById(leaveRequestModel.getUser_id()).orElse(null);

        LocalDate startDate = LocalDate.parse(leaveRequestModel.getStart_date());
        LocalDate endDate = LocalDate.parse(leaveRequestModel.getEnd_date());
        int year = startDate.getYear();

        int diffInDays = (int) (ChronoUnit.DAYS.between(startDate, endDate) + 1);

        LeaveBalanceEntity usedBalance = leaveBalanceRepository
                .findByUserAndLeaveTypeIdAndYear(user, leaveTypeId, year)
                .orElse(null);

        if (usedBalance != null) {
            if (leaveRequestModel.getStatus().equals("อนุมัติแล้ว")) {
                usedBalance.setRemainingDays(usedBalance.getRemainingDays() + diffInDays);
                leaveBalanceRepository.save(usedBalance);
            }
        } else {
            LeaveBalanceEntity leaveBalanceEntity = new LeaveBalanceEntity();
            leaveBalanceEntity.setUser(user);
            leaveBalanceEntity.setLeaveType(leaveTypeId);
            leaveBalanceEntity.setYear(year);


            leaveBalanceRepository.save(leaveBalanceEntity);
        }

    }

}


