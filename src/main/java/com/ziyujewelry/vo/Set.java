package com.ziyujewelry.vo;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @创建人: jianyu.quan
 * @E-mail: 164174421@qq.com
 * @创建时间: 2019年08月04 下午 16:09
 * @描述:
 */
public class Set implements Serializable {

	private Integer id;
	private User user;
	@Column(name = "dishA_img")
	private String dishAImg;
	@Column(name = "dishB_img")
	private String dishBImg;
	@Column(name = "wechat_pay")
	private String wechatPay;
	@Column(name = "alipay")
	private String aliPay;
	@Column(name = "is_default")
	private Integer isDefault;

	public Set() {}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDishAImg() {
		return dishAImg;
	}

	public void setDishAImg(String dishAImg) {
		this.dishAImg = dishAImg;
	}

	public String getDishBImg() {
		return dishBImg;
	}

	public void setDishBImg(String dishBImg) {
		this.dishBImg = dishBImg;
	}

	public String getWechatPay() {
		return wechatPay;
	}

	public void setWechatPay(String wechatPay) {
		this.wechatPay = wechatPay;
	}

	public String getAliPay() {
		return aliPay;
	}

	public void setAliPay(String aliPay) {
		this.aliPay = aliPay;
	}
}
