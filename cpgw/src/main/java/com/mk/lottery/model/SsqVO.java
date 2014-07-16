package com.mk.lottery.model;

import java.io.Serializable;

/**
 * 序列化一组彩票数据
 * @author Administrator
 *
 */
public class SsqVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8284104476055237910L;
	private String first;
	private String second;
	private String third;
	private String fourth;
	private String fifth;
	private String sixth;
	private String blue;
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
	public String getBlue() {
		return blue;
	}
	public void setBlue(String blue) {
		this.blue = blue;
	}
	
	
}
