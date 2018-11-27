package service.entity;

import java.util.Objects;

public class UserData {
    private String id;
    private int date;
    private String appName;
    private String channel;
    private int dailyNewUser;
    private int dailyActivityUser;
    private int startupTime;
    private String singleUseTime;
    private double retention;
    private String version;

    public  UserData (){
        this.id="null";
        this.version="1.0.0";
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", appName='" + appName + '\'' +
                ", channel='" + channel + '\'' +
                ", dailyNewUser=" + dailyNewUser +
                ", dailyActivityUser=" + dailyActivityUser +
                ", startupTime=" + startupTime +
                ", singleUseTime=" + singleUseTime +
                ", retention=" + retention +
                ", version='" + version + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return date == userData.date &&
                dailyNewUser == userData.dailyNewUser &&
                dailyActivityUser == userData.dailyActivityUser &&
                startupTime == userData.startupTime &&
                singleUseTime == userData.singleUseTime &&
                Double.compare(userData.retention, retention) == 0 &&
                Objects.equals(id, userData.id) &&
                Objects.equals(appName, userData.appName) &&
                Objects.equals(channel, userData.channel) &&
                Objects.equals(version, userData.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, appName, channel, dailyNewUser, dailyActivityUser, startupTime, singleUseTime, retention, version);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getDailyNewUser() {
        return dailyNewUser;
    }

    public void setDailyNewUser(int dailyNewUser) {
        this.dailyNewUser = dailyNewUser;
    }

    public int getDailyActivityUser() {
        return dailyActivityUser;
    }

    public void setDailyActivityUser(int dailyActivityUser) {
        this.dailyActivityUser = dailyActivityUser;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
