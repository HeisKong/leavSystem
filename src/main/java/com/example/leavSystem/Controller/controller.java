package com.example.leavSystem.Controller;

import com.example.leavSystem.Entity.LeaveRequestEntity;
import com.example.leavSystem.Entity.UserEntity;
import com.example.leavSystem.Model.LeaveBalanceModel;
import com.example.leavSystem.Model.LeaveRequestModel;
import com.example.leavSystem.Repository.LeaveRequestRepository;
import com.example.leavSystem.Repository.UserRepository;
import com.example.leavSystem.Service.LeaveBalanceService;
import com.example.leavSystem.Service.LeaveRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api")
public class controller {
    private final LeaveRequestService leaveRequestService;
    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveBalanceService leaveBalanceService;
    public controller(LeaveRequestService leaveRequestService, LeaveRequestRepository leaveRequestRepository,LeaveBalanceService leaveBalanceService) {
        this.leaveRequestService = leaveRequestService;
        this.leaveRequestRepository = leaveRequestRepository;
        this.leaveBalanceService = leaveBalanceService;
    }


    @PostMapping("/leave-requests")
    public void createLeaveRequest(@RequestBody LeaveRequestModel leaveRequestModel) {
        leaveRequestService.createLeaveRequest(leaveRequestModel);
    }

    @GetMapping("/leave-requests")
    public List<LeaveRequestModel> getAllLeaveRequests() {
        return leaveRequestService.getAll();
    }

    @PutMapping("/leave-request/{userId}")
    public void updateLeaveRequest(@PathVariable int userId, @RequestBody LeaveRequestModel leaveRequestModel) {
        leaveRequestService.updateLeaveRequest(userId,leaveRequestModel);
    }

    @GetMapping("/leave-balances")
    public LeaveBalanceModel getLeaveBalances() {
        return leaveBalanceService.getLeaveBalances();
    }
}
