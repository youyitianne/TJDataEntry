package service.entity;

import java.util.Objects;

public class PayObject {
    private String order_number;
    private String request_number;
    private Long order_time;
    private Long payment_time;
    private int duration;
    private String app_name;
    private String product_name;
    private String payment;
    private String currency_type;
    private String pay_type;
    private String marketint_channel;
    private String order_status;
    private String order_result;
    private String bussiness_type;
    private String original_order;
    private Long refund_time;
    private int refund_amount;
    private String country;
    private String channel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayObject payObject = (PayObject) o;
        return duration == payObject.duration &&
                refund_amount == payObject.refund_amount &&
                Objects.equals(order_number, payObject.order_number) &&
                Objects.equals(request_number, payObject.request_number) &&
                Objects.equals(order_time, payObject.order_time) &&
                Objects.equals(payment_time, payObject.payment_time) &&
                Objects.equals(app_name, payObject.app_name) &&
                Objects.equals(product_name, payObject.product_name) &&
                Objects.equals(payment, payObject.payment) &&
                Objects.equals(currency_type, payObject.currency_type) &&
                Objects.equals(pay_type, payObject.pay_type) &&
                Objects.equals(marketint_channel, payObject.marketint_channel) &&
                Objects.equals(order_status, payObject.order_status) &&
                Objects.equals(order_result, payObject.order_result) &&
                Objects.equals(bussiness_type, payObject.bussiness_type) &&
                Objects.equals(original_order, payObject.original_order) &&
                Objects.equals(refund_time, payObject.refund_time) &&
                Objects.equals(country, payObject.country) &&
                Objects.equals(channel, payObject.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_number, request_number, order_time, payment_time, duration, app_name, product_name, payment, currency_type, pay_type, marketint_channel, order_status, order_result, bussiness_type, original_order, refund_time, refund_amount, country, channel);
    }

    @Override
    public String toString() {
        return "PayObject{" +
                "order_number='" + order_number + '\'' +
                ", request_number=" + request_number +
                ", order_time=" + order_time +
                ", payment_time=" + payment_time +
                ", duration=" + duration +
                ", app_name='" + app_name + '\'' +
                ", product_name='" + product_name + '\'' +
                ", payment='" + payment + '\'' +
                ", currency_type='" + currency_type + '\'' +
                ", pay_type='" + pay_type + '\'' +
                ", marketint_channel='" + marketint_channel + '\'' +
                ", order_status='" + order_status + '\'' +
                ", order_result='" + order_result + '\'' +
                ", bussiness_type='" + bussiness_type + '\'' +
                ", original_order='" + original_order + '\'' +
                ", refund_time=" + refund_time +
                ", refund_amount=" + refund_amount +
                ", country='" + country + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getRequest_number() {
        return request_number;
    }

    public void setRequest_number(String request_number) {
        this.request_number = request_number;
    }

    public Long getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Long order_time) {
        this.order_time = order_time;
    }

    public Long getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Long payment_time) {
        this.payment_time = payment_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(String currency_type) {
        this.currency_type = currency_type;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getMarketint_channel() {
        return marketint_channel;
    }

    public void setMarketint_channel(String marketint_channel) {
        this.marketint_channel = marketint_channel;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_result() {
        return order_result;
    }

    public void setOrder_result(String order_result) {
        this.order_result = order_result;
    }

    public String getBussiness_type() {
        return bussiness_type;
    }

    public void setBussiness_type(String bussiness_type) {
        this.bussiness_type = bussiness_type;
    }

    public String getOriginal_order() {
        return original_order;
    }

    public void setOriginal_order(String original_order) {
        this.original_order = original_order;
    }

    public Long getRefund_time() {
        return refund_time;
    }

    public void setRefund_time(Long refund_time) {
        this.refund_time = refund_time;
    }

    public int getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(int refund_amount) {
        this.refund_amount = refund_amount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
