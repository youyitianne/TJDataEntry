package service.entity;

import java.util.Objects;

public class UserVO {
    private double income;
    private double dauarpu;
    private int newUser;
    private int activityUser;
    private int startupTime;
    private String singleUseTime;
    private double retention;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVO userVO = (UserVO) o;
        return Double.compare(userVO.income, income) == 0 &&
                Double.compare(userVO.dauarpu, dauarpu) == 0 &&
                newUser == userVO.newUser &&
                activityUser == userVO.activityUser &&
                startupTime == userVO.startupTime &&
                Double.compare(userVO.retention, retention) == 0 &&
                Objects.equals(singleUseTime, userVO.singleUseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(income, dauarpu, newUser, activityUser, startupTime, singleUseTime, retention);
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "income=" + income +
                ", dauarpu=" + dauarpu +
                ", newUser=" + newUser +
                ", activityUser=" + activityUser +
                ", startupTime=" + startupTime +
                ", singleUseTime='" + singleUseTime + '\'' +
                ", retention=" + retention +
                '}';
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getDauarpu() {
        return dauarpu;
    }

    public void setDauarpu(double dauarpu) {
        this.dauarpu = dauarpu;
    }

    public int getNewUser() {
        return newUser;
    }

    public void setNewUser(int newUser) {
        this.newUser = newUser;
    }

    public int getActivityUser() {
        return activityUser;
    }

    public void setActivityUser(int activityUser) {
        this.activityUser = activityUser;
    }

    public int getStartupTime() {
        return startupTime;
    }

    public void setStartupTime(int startupTime) {
        this.startupTime = startupTime;
    }

    public String getSingleUseTime() {
        return singleUseTime;
    }

    public void setSingleUseTime(String singleUseTime) {
        this.singleUseTime = singleUseTime;
    }

    public double getRetention() {
        return retention;
    }

    public void setRetention(double retention) {
        this.retention = retention;
    }
}
