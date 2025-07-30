package com.example.leavSystem.Model;

public class LeaveBalanceModel {
    private int user_id;
    private int leave_type_id;
    private int year;
    private int remainingDays;

    public LeaveBalanceModel() {}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLeave_type_id() {
        return leave_type_id;
    }

    public void setLeave_type_id(int leave_type_id) {
        this.leave_type_id = leave_type_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }
}
