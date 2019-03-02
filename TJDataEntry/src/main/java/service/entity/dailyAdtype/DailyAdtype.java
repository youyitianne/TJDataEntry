package service.entity.dailyAdtype;

import java.util.Objects;

public class DailyAdtype {
    private double earned;   //渠道收益
    private Double arpu;           //渠道arpu
    private int startup_time;   //一天一个渠道的启动次数
    private String single_use_time;   //一天一个渠道的单次启动时长
    private int dnu;   //一天一个渠道的新增人数
    private int dau;     //一天一个渠道的活跃人数
    private Double retention;   //一天一个渠道的日留存
    private DailyAdtypeChannel gdt_dac;   //一天一个渠道的广点通数据
    private DailyAdtypeChannel channel_dac;   //一天一个渠道的渠道数据
    private DailyAdtypeChannel tt_dac;   //一天一个渠道的头条数据

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyAdtype that = (DailyAdtype) o;
        return Objects.equals(earned, that.earned) &&
                Objects.equals(arpu, that.arpu) &&
                Objects.equals(startup_time, that.startup_time) &&
                Objects.equals(single_use_time, that.single_use_time) &&
                Objects.equals(dnu, that.dnu) &&
                Objects.equals(dau, that.dau) &&
                Objects.equals(retention, that.retention) &&
                Objects.equals(gdt_dac, that.gdt_dac) &&
                Objects.equals(channel_dac, that.channel_dac) &&
                Objects.equals(tt_dac, that.tt_dac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(earned, arpu, startup_time, single_use_time, dnu, dau, retention, gdt_dac, channel_dac, tt_dac);
    }

    @Override
    public String toString() {
        return "DailyAdtype{" +
                "earned=" + earned +
                ", arpu=" + arpu +
                ", startup_time=" + startup_time +
                ", single_use_time='" + single_use_time + '\'' +
                ", dnu=" + dnu +
                ", dau=" + dau +
                ", retention=" + retention +
                ", gdt_dac=" + gdt_dac +
                ", channel_dac=" + channel_dac +
                ", tt_dac=" + tt_dac +
                '}';
    }

    public double getEarned() {
        return earned;
    }

    public void setEarned(double earned) {
        this.earned = earned;
    }

    public Double getArpu() {
        return arpu;
    }

    public void setArpu(Double arpu) {
        this.arpu = arpu;
    }

    public int getStartup_time() {
        return startup_time;
    }

    public void setStartup_time(int startup_time) {
        this.startup_time = startup_time;
    }

    public String getSingle_use_time() {
        return single_use_time;
    }

    public void setSingle_use_time(String single_use_time) {
        this.single_use_time = single_use_time;
    }

    public int getDnu() {
        return dnu;
    }

    public void setDnu(int dnu) {
        this.dnu = dnu;
    }

    public int getDau() {
        return dau;
    }

    public void setDau(int dau) {
        this.dau = dau;
    }

    public Double getRetention() {
        return retention;
    }

    public void setRetention(Double retention) {
        this.retention = retention;
    }

    public DailyAdtypeChannel getGdt_dac() {
        return gdt_dac;
    }

    public void setGdt_dac(DailyAdtypeChannel gdt_dac) {
        this.gdt_dac = gdt_dac;
    }

    public DailyAdtypeChannel getChannel_dac() {
        return channel_dac;
    }

    public void setChannel_dac(DailyAdtypeChannel channel_dac) {
        this.channel_dac = channel_dac;
    }

    public DailyAdtypeChannel getTt_dac() {
        return tt_dac;
    }

    public void setTt_dac(DailyAdtypeChannel tt_dac) {
        this.tt_dac = tt_dac;
    }
}
