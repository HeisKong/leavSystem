package com.example.leavSystem.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "leave_requests")
public class LeaveRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveTypeEntity leaveTypeId;

    @Column(name = "start_date")
    private String start_date;

    @Column(name = "end_date")
    private String end_date;

    @Column(name = "status")
    private String status;

    @Column(name = "reason")
    private String reason;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LeaveTypeEntity getLeaveType() {
        return leaveTypeId;
    }

    public void setLeaveType(LeaveTypeEntity leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getStartDate() {
        return start_date;
    }
    public void setStartDate(String start_date) {
        this.start_date = start_date;
    }

    public String getEndDate() {
        return end_date;
    }
    public void setEndDate(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
}
