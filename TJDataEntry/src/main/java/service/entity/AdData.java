package service.entity;

import java.util.Objects;

public class AdData {
    private String id;
    private int date;
    private String appName;
    private String channel;
    private String adType;
    private double earned;
    private double clickRate;
    private double ecpm;
    private int impression;
    private int click;
    private double fillRate;
    private String platform;
    private String note;
    private String sdk_name;

    public AdData() {
        this.id = "null";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdData adData = (AdData) o;
        return date == adData.date &&
                Double.compare(adData.earned, earned) == 0 &&
                Double.compare(adData.clickRate, clickRate) == 0 &&
                Double.compare(adData.ecpm, ecpm) == 0 &&
                impression == adData.impression &&
                click == adData.click &&
                Double.compare(adData.fillRate, fillRate) == 0 &&
                Objects.equals(id, adData.id) &&
                Objects.equals(appName, adData.appName) &&
                Objects.equals(channel, adData.channel) &&
                Objects.equals(adType, adData.adType) &&
                Objects.equals(platform, adData.platform) &&
                Objects.equals(note, adData.note) &&
                Objects.equals(sdk_name, adData.sdk_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, appName, channel, adType, earned, clickRate, ecpm, impression, click, fillRate, platform, note, sdk_name);
    }

    @Override
    public String toString() {
        return "AdData{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", appName='" + appName + '\'' +
                ", channel='" + channel + '\'' +
                ", adType='" + adType + '\'' +
                ", earned=" + earned +
                ", clickRate=" + clickRate +
                ", ecpm=" + ecpm +
                ", impression=" + impression +
                ", click=" + click +
                ", fillRate=" + fillRate +
                ", platform='" + platform + '\'' +
                ", note='" + note + '\'' +
                ", sdk_name='" + sdk_name + '\'' +
                '}';
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

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public double getEarned() {
        return earned;
    }

    public void setEarned(double earned) {
        this.earned = earned;
    }

    public double getClickRate() {
        return clickRate;
    }

    public void setClickRate(double clickRate) {
        this.clickRate = clickRate;
    }

    public double getEcpm() {
        return ecpm;
    }

    public void setEcpm(double ecpm) {
        this.ecpm = ecpm;
    }

    public int getImpression() {
        return impression;
    }

    public void setImpression(int impression) {
        this.impression = impression;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public double getFillRate() {
        return fillRate;
    }

    public void setFillRate(double fillRate) {
        this.fillRate = fillRate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSdk_name() {
        return sdk_name;
    }

    public void setSdk_name(String sdk_name) {
        this.sdk_name = sdk_name;
    }
}
