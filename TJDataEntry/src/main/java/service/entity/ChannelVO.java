package service.entity;

import java.util.List;
import java.util.Objects;

public class ChannelVO {
    private String date;
    private String name;
    private UserVO userVO;
    private List<AdDataVO> guang;
    private Double guangdiantong_income;
    private Double guangdiantong_dauarpu;
    private List<AdDataVO> qudao;
    private Double qudao_income;
    private Double qudao_dauarpu;
    private List<AdDataVO> yixin;
    private Double yixin_income;
    private Double yixin_dauarpu;
    private List<AdDataVO> toutiao;
    private Double toutiao_income;
    private Double toutiao_dauarpu;
    private Double channel_income_all;
    private Double chhannel_dauarpu_all;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelVO channelVO = (ChannelVO) o;
        return Objects.equals(date, channelVO.date) &&
                Objects.equals(name, channelVO.name) &&
                Objects.equals(userVO, channelVO.userVO) &&
                Objects.equals(guang, channelVO.guang) &&
                Objects.equals(guangdiantong_income, channelVO.guangdiantong_income) &&
                Objects.equals(guangdiantong_dauarpu, channelVO.guangdiantong_dauarpu) &&
                Objects.equals(qudao, channelVO.qudao) &&
                Objects.equals(qudao_income, channelVO.qudao_income) &&
                Objects.equals(qudao_dauarpu, channelVO.qudao_dauarpu) &&
                Objects.equals(yixin, channelVO.yixin) &&
                Objects.equals(yixin_income, channelVO.yixin_income) &&
                Objects.equals(yixin_dauarpu, channelVO.yixin_dauarpu) &&
                Objects.equals(toutiao, channelVO.toutiao) &&
                Objects.equals(toutiao_income, channelVO.toutiao_income) &&
                Objects.equals(toutiao_dauarpu, channelVO.toutiao_dauarpu) &&
                Objects.equals(channel_income_all, channelVO.channel_income_all) &&
                Objects.equals(chhannel_dauarpu_all, channelVO.chhannel_dauarpu_all);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, name, userVO, guang, guangdiantong_income, guangdiantong_dauarpu, qudao, qudao_income, qudao_dauarpu, yixin, yixin_income, yixin_dauarpu, toutiao, toutiao_income, toutiao_dauarpu, channel_income_all, chhannel_dauarpu_all);
    }

    @Override
    public String toString() {
        return "ChannelVO{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", userVO=" + userVO +
                ", guang=" + guang +
                ", guangdiantong_income=" + guangdiantong_income +
                ", guangdiantong_dauarpu=" + guangdiantong_dauarpu +
                ", qudao=" + qudao +
                ", qudao_income=" + qudao_income +
                ", qudao_dauarpu=" + qudao_dauarpu +
                ", yixin=" + yixin +
                ", yixin_income=" + yixin_income +
                ", yixin_dauarpu=" + yixin_dauarpu +
                ", toutiao=" + toutiao +
                ", toutiao_income=" + toutiao_income +
                ", toutiao_dauarpu=" + toutiao_dauarpu +
                ", channel_income_all=" + channel_income_all +
                ", chhannel_dauarpu_all=" + chhannel_dauarpu_all +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public List<AdDataVO> getGuang() {
        return guang;
    }

    public void setGuang(List<AdDataVO> guang) {
        this.guang = guang;
    }

    public Double getGuangdiantong_income() {
        return guangdiantong_income;
    }

    public void setGuangdiantong_income(Double guangdiantong_income) {
        this.guangdiantong_income = guangdiantong_income;
    }

    public Double getGuangdiantong_dauarpu() {
        return guangdiantong_dauarpu;
    }

    public void setGuangdiantong_dauarpu(Double guangdiantong_dauarpu) {
        this.guangdiantong_dauarpu = guangdiantong_dauarpu;
    }

    public List<AdDataVO> getQudao() {
        return qudao;
    }

    public void setQudao(List<AdDataVO> qudao) {
        this.qudao = qudao;
    }

    public Double getQudao_income() {
        return qudao_income;
    }

    public void setQudao_income(Double qudao_income) {
        this.qudao_income = qudao_income;
    }

    public Double getQudao_dauarpu() {
        return qudao_dauarpu;
    }

    public void setQudao_dauarpu(Double qudao_dauarpu) {
        this.qudao_dauarpu = qudao_dauarpu;
    }

    public List<AdDataVO> getYixin() {
        return yixin;
    }

    public void setYixin(List<AdDataVO> yixin) {
        this.yixin = yixin;
    }

    public Double getYixin_income() {
        return yixin_income;
    }

    public void setYixin_income(Double yixin_income) {
        this.yixin_income = yixin_income;
    }

    public Double getYixin_dauarpu() {
        return yixin_dauarpu;
    }

    public void setYixin_dauarpu(Double yixin_dauarpu) {
        this.yixin_dauarpu = yixin_dauarpu;
    }

    public List<AdDataVO> getToutiao() {
        return toutiao;
    }

    public void setToutiao(List<AdDataVO> toutiao) {
        this.toutiao = toutiao;
    }

    public Double getToutiao_income() {
        return toutiao_income;
    }

    public void setToutiao_income(Double toutiao_income) {
        this.toutiao_income = toutiao_income;
    }

    public Double getToutiao_dauarpu() {
        return toutiao_dauarpu;
    }

    public void setToutiao_dauarpu(Double toutiao_dauarpu) {
        this.toutiao_dauarpu = toutiao_dauarpu;
    }

    public Double getChannel_income_all() {
        return channel_income_all;
    }

    public void setChannel_income_all(Double channel_income_all) {
        this.channel_income_all = channel_income_all;
    }

    public Double getChhannel_dauarpu_all() {
        return chhannel_dauarpu_all;
    }

    public void setChhannel_dauarpu_all(Double chhannel_dauarpu_all) {
        this.chhannel_dauarpu_all = chhannel_dauarpu_all;
    }
}
