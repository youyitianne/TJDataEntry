package service.entity;

import java.util.Objects;

public class ArpuData {
    private Double toutiao_earned;
    private Double channel_earned;
    private Double gdt_earned;
    private Double all_earned;
    private Integer active_user;
    private Double toutiao_arpu;
    private Double channel_arpu;
    private Double gdt_arpu;
    private Double all_arpu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArpuData arpuData = (ArpuData) o;
        return Objects.equals(toutiao_earned, arpuData.toutiao_earned) &&
                Objects.equals(channel_earned, arpuData.channel_earned) &&
                Objects.equals(gdt_earned, arpuData.gdt_earned) &&
                Objects.equals(all_earned, arpuData.all_earned) &&
                Objects.equals(active_user, arpuData.active_user) &&
                Objects.equals(toutiao_arpu, arpuData.toutiao_arpu) &&
                Objects.equals(channel_arpu, arpuData.channel_arpu) &&
                Objects.equals(gdt_arpu, arpuData.gdt_arpu) &&
                Objects.equals(all_arpu, arpuData.all_arpu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toutiao_earned, channel_earned, gdt_earned, all_earned, active_user, toutiao_arpu, channel_arpu, gdt_arpu, all_arpu);
    }

    @Override
    public String toString() {
        return "ArpuData{" +
                "toutiao_earned=" + toutiao_earned +
                ", channel_earned=" + channel_earned +
                ", gdt_earned=" + gdt_earned +
                ", all_earned=" + all_earned +
                ", active_user=" + active_user +
                ", toutiao_arpu=" + toutiao_arpu +
                ", channel_arpu=" + channel_arpu +
                ", gdt_arpu=" + gdt_arpu +
                ", all_arpu=" + all_arpu +
                '}';
    }

    public Double getToutiao_earned() {
        return toutiao_earned;
    }

    public void setToutiao_earned(Double toutiao_earned) {
        this.toutiao_earned = toutiao_earned;
    }

    public Double getChannel_earned() {
        return channel_earned;
    }

    public void setChannel_earned(Double channel_earned) {
        this.channel_earned = channel_earned;
    }

    public Double getGdt_earned() {
        return gdt_earned;
    }

    public void setGdt_earned(Double gdt_earned) {
        this.gdt_earned = gdt_earned;
    }

    public Double getAll_earned() {
        return all_earned;
    }

    public void setAll_earned(Double all_earned) {
        this.all_earned = all_earned;
    }

    public Integer getActive_user() {
        return active_user;
    }

    public void setActive_user(Integer active_user) {
        this.active_user = active_user;
    }

    public Double getToutiao_arpu() {
        return toutiao_arpu;
    }

    public void setToutiao_arpu(Double toutiao_arpu) {
        this.toutiao_arpu = toutiao_arpu;
    }

    public Double getChannel_arpu() {
        return channel_arpu;
    }

    public void setChannel_arpu(Double channel_arpu) {
        this.channel_arpu = channel_arpu;
    }

    public Double getGdt_arpu() {
        return gdt_arpu;
    }

    public void setGdt_arpu(Double gdt_arpu) {
        this.gdt_arpu = gdt_arpu;
    }

    public Double getAll_arpu() {
        return all_arpu;
    }

    public void setAll_arpu(Double all_arpu) {
        this.all_arpu = all_arpu;
    }
}
