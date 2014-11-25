package com.mk.lottery.model;

import java.io.Serializable;
import java.util.List;

/**
 * 序列化一组彩票数据
 * @author Administrator
 *
 */
public class LottoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8284104476055237910L;
    /** 类型 1-双色球 2-大乐透*/
    private int type;
    /** 选中的第一个球*/
	private String first;
    /** 选中的第二个球*/
	private String second;
    /** 选中的第三个球*/
	private String third;
    /** 选中的第四个球*/
	private String fourth;
    /** 选中的第五个球*/
	private String fifth;
    /** 选中的第六个球*/
	private String sixth;
    /** 选中的第七个球*/
	private String seventh;
    /** 一等奖期号LIST*/
    private List<Integer> firstPrizeList;
    /** 二等奖期号LIST*/
    private List<Integer> secondPrizeList;
    /** 三等奖期号LIST*/
    private List<Integer> thirdPrizeList;
    /** 四等奖期号LIST*/
    private List<Integer> fourthPrizeList;
    /** 五等奖期号LIST*/
    private List<Integer> fifthPrizeList;
    /** 六等奖期号LIST*/
    private List<Integer> sixthPrizeList;


	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getThird() {
		return third;
	}
	public void setThird(String third) {
		this.third = third;
	}
	public String getFourth() {
		return fourth;
	}
	public void setFourth(String fourth) {
		this.fourth = fourth;
	}
	public String getFifth() {
		return fifth;
	}
	public void setFifth(String fifth) {
		this.fifth = fifth;
	}
	public String getSixth() {
		return sixth;
	}
	public void setSixth(String sixth) {
		this.sixth = sixth;
	}

    public String getSeventh() {
        return seventh;
    }

    public void setSeventh(String seventh) {
        this.seventh = seventh;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Integer> getFirstPrizeList() {
        return firstPrizeList;
    }

    public void setFirstPrizeList(List<Integer> firstPrizeList) {
        this.firstPrizeList = firstPrizeList;
    }

    public List<Integer> getSecondPrizeList() {
        return secondPrizeList;
    }

    public void setSecondPrizeList(List<Integer> secondPrizeList) {
        this.secondPrizeList = secondPrizeList;
    }

    public List<Integer> getThirdPrizeList() {
        return thirdPrizeList;
    }

    public void setThirdPrizeList(List<Integer> thirdPrizeList) {
        this.thirdPrizeList = thirdPrizeList;
    }

    public List<Integer> getFourthPrizeList() {
        return fourthPrizeList;
    }

    public void setFourthPrizeList(List<Integer> fourthPrizeList) {
        this.fourthPrizeList = fourthPrizeList;
    }

    public List<Integer> getFifthPrizeList() {
        return fifthPrizeList;
    }

    public void setFifthPrizeList(List<Integer> fifthPrizeList) {
        this.fifthPrizeList = fifthPrizeList;
    }

    public List<Integer> getSixthPrizeList() {
        return sixthPrizeList;
    }

    public void setSixthPrizeList(List<Integer> sixthPrizeList) {
        this.sixthPrizeList = sixthPrizeList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
