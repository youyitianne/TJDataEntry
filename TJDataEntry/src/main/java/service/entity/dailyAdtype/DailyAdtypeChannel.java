package service.entity.dailyAdtype;

import service.pubmethod.InitConf;

import java.util.Objects;

public class DailyAdtypeChannel {
    private double totalEarned;
    private int totalClick;
    private int totalImpression;
    //横幅普通
    private double banner_pt_earned;
    private int banner_pt_click;
    private int banner_pt_impression;
    //插屏普通
    private double inline_pt_earned;
    private int inline_pt_click;
    private int inline_pt_impression;
    //插屏渲染
    private double inline_xr_earned;
    private int inline_xr_click;
    private int inline_xr_impression;
    //插屏模版
    private double inline_mb_earned;
    private int inline_mb_click;
    private int inline_mb_impression;
    //开屏普通
    private double splash_pt_earned;
    private int splash_pt_click;
    private int splash_pt_impression;
    //开屏渲染
    private double splash_xr_earned;
    private int splash_xr_click;
    private int splash_xr_impression;
    //开屏模版
    private double splash_mb_earned;
    private int splash_mb_click;
    private int splash_mb_impression;
    //视频普通
    private double video_pt_earned;
    private int video_pt_click;
    private int video_pt_impression;
    //视频激励
    private double video_jl_earned;
    private int video_jl_click;
    private int video_jl_impression;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyAdtypeChannel channel = (DailyAdtypeChannel) o;
        return Double.compare(channel.totalEarned, totalEarned) == 0 &&
                Double.compare(channel.banner_pt_earned, banner_pt_earned) == 0 &&
                Double.compare(channel.inline_pt_earned, inline_pt_earned) == 0 &&
                Double.compare(channel.inline_xr_earned, inline_xr_earned) == 0 &&
                Double.compare(channel.inline_mb_earned, inline_mb_earned) == 0 &&
                Double.compare(channel.splash_pt_earned, splash_pt_earned) == 0 &&
                Double.compare(channel.splash_xr_earned, splash_xr_earned) == 0 &&
                Double.compare(channel.splash_mb_earned, splash_mb_earned) == 0 &&
                Double.compare(channel.video_pt_earned, video_pt_earned) == 0 &&
                Double.compare(channel.video_jl_earned, video_jl_earned) == 0 &&
                Objects.equals(totalClick, channel.totalClick) &&
                Objects.equals(totalImpression, channel.totalImpression) &&
                Objects.equals(banner_pt_click, channel.banner_pt_click) &&
                Objects.equals(banner_pt_impression, channel.banner_pt_impression) &&
                Objects.equals(inline_pt_click, channel.inline_pt_click) &&
                Objects.equals(inline_pt_impression, channel.inline_pt_impression) &&
                Objects.equals(inline_xr_click, channel.inline_xr_click) &&
                Objects.equals(inline_xr_impression, channel.inline_xr_impression) &&
                Objects.equals(inline_mb_click, channel.inline_mb_click) &&
                Objects.equals(inline_mb_impression, channel.inline_mb_impression) &&
                Objects.equals(splash_pt_click, channel.splash_pt_click) &&
                Objects.equals(splash_pt_impression, channel.splash_pt_impression) &&
                Objects.equals(splash_xr_click, channel.splash_xr_click) &&
                Objects.equals(splash_xr_impression, channel.splash_xr_impression) &&
                Objects.equals(splash_mb_click, channel.splash_mb_click) &&
                Objects.equals(splash_mb_impression, channel.splash_mb_impression) &&
                Objects.equals(video_pt_click, channel.video_pt_click) &&
                Objects.equals(video_pt_impression, channel.video_pt_impression) &&
                Objects.equals(video_jl_click, channel.video_jl_click) &&
                Objects.equals(video_jl_impression, channel.video_jl_impression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalEarned, totalClick, totalImpression, banner_pt_earned, banner_pt_click, banner_pt_impression, inline_pt_earned, inline_pt_click, inline_pt_impression, inline_xr_earned, inline_xr_click, inline_xr_impression, inline_mb_earned, inline_mb_click, inline_mb_impression, splash_pt_earned, splash_pt_click, splash_pt_impression, splash_xr_earned, splash_xr_click, splash_xr_impression, splash_mb_earned, splash_mb_click, splash_mb_impression, video_pt_earned, video_pt_click, video_pt_impression, video_jl_earned, video_jl_click, video_jl_impression);
    }

    @Override
    public String toString() {
        return "DailyAdtypeChannel{" +
                "totalEarned=" + totalEarned +
                ", totalClick=" + totalClick +
                ", totalImpression=" + totalImpression +
                ", banner_pt_earned=" + banner_pt_earned +
                ", banner_pt_click=" + banner_pt_click +
                ", banner_pt_impression=" + banner_pt_impression +
                ", inline_pt_earned=" + inline_pt_earned +
                ", inline_pt_click=" + inline_pt_click +
                ", inline_pt_impression=" + inline_pt_impression +
                ", inline_xr_earned=" + inline_xr_earned +
                ", inline_xr_click=" + inline_xr_click +
                ", inline_xr_impression=" + inline_xr_impression +
                ", inline_mb_earned=" + inline_mb_earned +
                ", inline_mb_click=" + inline_mb_click +
                ", inline_mb_impression=" + inline_mb_impression +
                ", splash_pt_earned=" + splash_pt_earned +
                ", splash_pt_click=" + splash_pt_click +
                ", splash_pt_impression=" + splash_pt_impression +
                ", splash_xr_earned=" + splash_xr_earned +
                ", splash_xr_click=" + splash_xr_click +
                ", splash_xr_impression=" + splash_xr_impression +
                ", splash_mb_earned=" + splash_mb_earned +
                ", splash_mb_click=" + splash_mb_click +
                ", splash_mb_impression=" + splash_mb_impression +
                ", video_pt_earned=" + video_pt_earned +
                ", video_pt_click=" + video_pt_click +
                ", video_pt_impression=" + video_pt_impression +
                ", video_jl_earned=" + video_jl_earned +
                ", video_jl_click=" + video_jl_click +
                ", video_jl_impression=" + video_jl_impression +
                '}';
    }

    public double getTotalEarned() {
        return totalEarned;
    }

    public void setTotalEarned(double totalEarned) {
        this.totalEarned = totalEarned;
    }

    public int getTotalClick() {
        return totalClick;
    }

    public void setTotalClick(int totalClick) {
        this.totalClick = totalClick;
    }

    public int getTotalImpression() {
        return totalImpression;
    }

    public void setTotalImpression(int totalImpression) {
        this.totalImpression = totalImpression;
    }

    public double getBanner_pt_earned() {
        return banner_pt_earned;
    }

    public void setBanner_pt_earned(double banner_pt_earned) {
        this.banner_pt_earned = banner_pt_earned;
    }

    public int getBanner_pt_click() {
        return banner_pt_click;
    }

    public void setBanner_pt_click(int banner_pt_click) {
        this.banner_pt_click = banner_pt_click;
    }

    public int getBanner_pt_impression() {
        return banner_pt_impression;
    }

    public void setBanner_pt_impression(int banner_pt_impression) {
        this.banner_pt_impression = banner_pt_impression;
    }

    public double getInline_pt_earned() {
        return inline_pt_earned;
    }

    public void setInline_pt_earned(double inline_pt_earned) {
        this.inline_pt_earned = inline_pt_earned;
    }

    public int getInline_pt_click() {
        return inline_pt_click;
    }

    public void setInline_pt_click(int inline_pt_click) {
        this.inline_pt_click = inline_pt_click;
    }

    public int getInline_pt_impression() {
        return inline_pt_impression;
    }

    public void setInline_pt_impression(int inline_pt_impression) {
        this.inline_pt_impression = inline_pt_impression;
    }

    public double getInline_xr_earned() {
        return inline_xr_earned;
    }

    public void setInline_xr_earned(double inline_xr_earned) {
        this.inline_xr_earned = inline_xr_earned;
    }

    public int getInline_xr_click() {
        return inline_xr_click;
    }

    public void setInline_xr_click(int inline_xr_click) {
        this.inline_xr_click = inline_xr_click;
    }

    public int getInline_xr_impression() {
        return inline_xr_impression;
    }

    public void setInline_xr_impression(int inline_xr_impression) {
        this.inline_xr_impression = inline_xr_impression;
    }

    public double getInline_mb_earned() {
        return inline_mb_earned;
    }

    public void setInline_mb_earned(double inline_mb_earned) {
        this.inline_mb_earned = inline_mb_earned;
    }

    public int getInline_mb_click() {
        return inline_mb_click;
    }

    public void setInline_mb_click(int inline_mb_click) {
        this.inline_mb_click = inline_mb_click;
    }

    public int getInline_mb_impression() {
        return inline_mb_impression;
    }

    public void setInline_mb_impression(int inline_mb_impression) {
        this.inline_mb_impression = inline_mb_impression;
    }

    public double getSplash_pt_earned() {
        return splash_pt_earned;
    }

    public void setSplash_pt_earned(double splash_pt_earned) {
        this.splash_pt_earned = splash_pt_earned;
    }

    public int getSplash_pt_click() {
        return splash_pt_click;
    }

    public void setSplash_pt_click(int splash_pt_click) {
        this.splash_pt_click = splash_pt_click;
    }

    public int getSplash_pt_impression() {
        return splash_pt_impression;
    }

    public void setSplash_pt_impression(int splash_pt_impression) {
        this.splash_pt_impression = splash_pt_impression;
    }

    public double getSplash_xr_earned() {
        return splash_xr_earned;
    }

    public void setSplash_xr_earned(double splash_xr_earned) {
        this.splash_xr_earned = splash_xr_earned;
    }

    public int getSplash_xr_click() {
        return splash_xr_click;
    }

    public void setSplash_xr_click(int splash_xr_click) {
        this.splash_xr_click = splash_xr_click;
    }

    public int getSplash_xr_impression() {
        return splash_xr_impression;
    }

    public void setSplash_xr_impression(int splash_xr_impression) {
        this.splash_xr_impression = splash_xr_impression;
    }

    public double getSplash_mb_earned() {
        return splash_mb_earned;
    }

    public void setSplash_mb_earned(double splash_mb_earned) {
        this.splash_mb_earned = splash_mb_earned;
    }

    public int getSplash_mb_click() {
        return splash_mb_click;
    }

    public void setSplash_mb_click(int splash_mb_click) {
        this.splash_mb_click = splash_mb_click;
    }

    public int getSplash_mb_impression() {
        return splash_mb_impression;
    }

    public void setSplash_mb_impression(int splash_mb_impression) {
        this.splash_mb_impression = splash_mb_impression;
    }

    public double getVideo_pt_earned() {
        return video_pt_earned;
    }

    public void setVideo_pt_earned(double video_pt_earned) {
        this.video_pt_earned = video_pt_earned;
    }

    public int getVideo_pt_click() {
        return video_pt_click;
    }

    public void setVideo_pt_click(int video_pt_click) {
        this.video_pt_click = video_pt_click;
    }

    public int getVideo_pt_impression() {
        return video_pt_impression;
    }

    public void setVideo_pt_impression(int video_pt_impression) {
        this.video_pt_impression = video_pt_impression;
    }

    public double getVideo_jl_earned() {
        return video_jl_earned;
    }

    public void setVideo_jl_earned(double video_jl_earned) {
        this.video_jl_earned = video_jl_earned;
    }

    public int getVideo_jl_click() {
        return video_jl_click;
    }

    public void setVideo_jl_click(int video_jl_click) {
        this.video_jl_click = video_jl_click;
    }

    public int getVideo_jl_impression() {
        return video_jl_impression;
    }

    public void setVideo_jl_impression(int video_jl_impression) {
        this.video_jl_impression = video_jl_impression;
    }
}
