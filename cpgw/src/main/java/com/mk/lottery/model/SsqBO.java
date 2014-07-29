package com.mk.lottery.model;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2014/7/10.
 */
public class SsqBO {
    /** id*/
    private int id;
    /** 开奖期号*/
    private int lotteryIssue;
    /** 开奖日期*/
    private String lotteryDate;
    /** 红号1*/
    private String red1;
    /** 红号2*/
    private String red2;
    /** 红号3*/
    private String red3;
    /** 红号4*/
    private String red4;
    /** 红号5*/
    private String red5;
    /** 红号6*/
    private String red6;
    /** 蓝号*/
    private String blue;
    /** 红号顺序1*/
    private String reds1;
    /** 红号顺序2*/
    private String reds2;
    /** 红号顺序3*/
    private String reds3;
    /** 红号顺序4*/
    private String reds4;
    /** 红号顺序5*/
    private String reds5;
    /** 红号顺序6*/
    private String reds6;
    /** 投注总额*/
    private int totalAmount;
    /** 奖池金额*/
    private int poolAmount;
    /** 一等奖注数*/
    private int firstCount;
    /** 一等奖金额*/
    private int firstAmount;
    /** 二等奖注数*/
    private int secondCount;
    /** 二等奖金额*/
    private int secondAmount;
    /** 三等奖注数*/
    private int thirdCount;
    /** 三等奖金额*/
    private int thirdAmount;
    /** 四等奖注数*/
    private int fourthCount;
    /** 四等奖金额*/
    private int fourthAmount;
    /** 五等奖注数*/
    private int fifthCount;
    /** 五等奖金额*/
    private int fifthAmount;
    /** 六等奖注数*/
    private int sixthCount;
    /** 六等奖金额*/
    private int sixthAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLotteryIssue() {
        return lotteryIssue;
    }

    public void setLotteryIssue(int lotteryIssue) {
        this.lotteryIssue = lotteryIssue;
    }

    public String getLotteryDate() {
        return lotteryDate;
    }

    public void setLotteryDate(String lotteryDate) {
        this.lotteryDate = lotteryDate;
    }

    public String getRed1() {
        return red1;
    }

    public void setRed1(String red1) {
        this.red1 = red1;
    }

    public String getRed2() {
        return red2;
    }

    public void setRed2(String red2) {
        this.red2 = red2;
    }

    public String getRed3() {
        return red3;
    }

    public void setRed3(String red3) {
        this.red3 = red3;
    }

    public String getRed4() {
        return red4;
    }

    public void setRed4(String red4) {
        this.red4 = red4;
    }

    public String getRed5() {
        return red5;
    }

    public void setRed5(String red5) {
        this.red5 = red5;
    }

    public String getRed6() {
        return red6;
    }

    public void setRed6(String red6) {
        this.red6 = red6;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    public String getReds1() {
        return reds1;
    }

    public void setReds1(String reds1) {
        this.reds1 = reds1;
    }

    public String getReds2() {
        return reds2;
    }

    public void setReds2(String reds2) {
        this.reds2 = reds2;
    }

    public String getReds3() {
        return reds3;
    }

    public void setReds3(String reds3) {
        this.reds3 = reds3;
    }

    public String getReds4() {
        return reds4;
    }

    public void setReds4(String reds4) {
        this.reds4 = reds4;
    }

    public String getReds5() {
        return reds5;
    }

    public void setReds5(String reds5) {
        this.reds5 = reds5;
    }

    public String getReds6() {
        return reds6;
    }

    public void setReds6(String reds6) {
        this.reds6 = reds6;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPoolAmount() {
        return poolAmount;
    }

    public void setPoolAmount(int poolAmount) {
        this.poolAmount = poolAmount;
    }

    public int getFirstCount() {
        return firstCount;
    }

    public void setFirstCount(int firstCount) {
        this.firstCount = firstCount;
    }

    public int getFirstAmount() {
        return firstAmount;
    }

    public void setFirstAmount(int firstAmount) {
        this.firstAmount = firstAmount;
    }

    public int getSecondCount() {
        return secondCount;
    }

    public void setSecondCount(int secondCount) {
        this.secondCount = secondCount;
    }

    public int getSecondAmount() {
        return secondAmount;
    }

    public void setSecondAmount(int secondAmount) {
        this.secondAmount = secondAmount;
    }

    public int getThirdCount() {
        return thirdCount;
    }

    public void setThirdCount(int thirdCount) {
        this.thirdCount = thirdCount;
    }

    public int getThirdAmount() {
        return thirdAmount;
    }

    public void setThirdAmount(int thirdAmount) {
        this.thirdAmount = thirdAmount;
    }

    public int getFourthCount() {
        return fourthCount;
    }

    public void setFourthCount(int fourthCount) {
        this.fourthCount = fourthCount;
    }

    public int getFourthAmount() {
        return fourthAmount;
    }

    public void setFourthAmount(int fourthAmount) {
        this.fourthAmount = fourthAmount;
    }

    public int getFifthCount() {
        return fifthCount;
    }

    public void setFifthCount(int fifthCount) {
        this.fifthCount = fifthCount;
    }

    public int getFifthAmount() {
        return fifthAmount;
    }

    public void setFifthAmount(int fifthAmount) {
        this.fifthAmount = fifthAmount;
    }

    public int getSixthCount() {
        return sixthCount;
    }

    public void setSixthCount(int sixthCount) {
        this.sixthCount = sixthCount;
    }

    public int getSixthAmount() {
        return sixthAmount;
    }

    public void setSixthAmount(int sixthAmount) {
        this.sixthAmount = sixthAmount;
    }
}
