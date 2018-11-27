package service.entity;

import java.util.Objects;

public class AdDataVO {
    private double income;
    private double clickRate;
    private double ecpm;
    private int impressions;
    private double perImpression;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdDataVO adDataVO = (AdDataVO) o;
        return Double.compare(adDataVO.income, income) == 0 &&
                Double.compare(adDataVO.clickRate, clickRate) == 0 &&
                Double.compare(adDataVO.ecpm, ecpm) == 0 &&
                impressions == adDataVO.impressions &&
                Double.compare(adDataVO.perImpression, perImpression) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(income, clickRate, ecpm, impressions, perImpression);
    }

    @Override
    public String toString() {
        return "AdDataVO{" +
                "income=" + income +
                ", clickRate=" + clickRate +
                ", ecpm=" + ecpm +
                ", impressions=" + impressions +
                ", perImpression=" + perImpression +
                '}';
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
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

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    public double getPerImpression() {
        return perImpression;
    }

    public void setPerImpression(double perImpression) {
        this.perImpression = perImpression;
    }
}
