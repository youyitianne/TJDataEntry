package service.entity;

import java.util.List;
import java.util.Objects;

public class TotalVO {
    private String date;
    private double income;
    private int newUser;
    private int activityUser;
    private double retention;
    private double dauarpu;
    private List<ChannelVO> channelVOList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalVO totalVO = (TotalVO) o;
        return Double.compare(totalVO.income, income) == 0 &&
                newUser == totalVO.newUser &&
                activityUser == totalVO.activityUser &&
                Double.compare(totalVO.retention, retention) == 0 &&
                Double.compare(totalVO.dauarpu, dauarpu) == 0 &&
                Objects.equals(date, totalVO.date) &&
                Objects.equals(channelVOList, totalVO.channelVOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, income, newUser, activityUser, retention, dauarpu, channelVOList);
    }

    @Override
    public String toString() {
        return "TotalVO{" +
                "date='" + date + '\'' +
                ", income=" + income +
                ", newUser=" + newUser +
                ", activityUser=" + activityUser +
                ", retention=" + retention +
                ", dauarpu=" + dauarpu +
                ", channelVOList=" + channelVOList +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
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

    public double getRetention() {
        return retention;
    }

    public void setRetention(double retention) {
        this.retention = retention;
    }

    public double getDauarpu() {
        return dauarpu;
    }

    public void setDauarpu(double dauarpu) {
        this.dauarpu = dauarpu;
    }

    public List<ChannelVO> getChannelVOList() {
        return channelVOList;
    }

    public void setChannelVOList(List<ChannelVO> channelVOList) {
        this.channelVOList = channelVOList;
    }
}
