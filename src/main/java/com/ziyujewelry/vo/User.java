package com.ziyujewelry.vo;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @创建人: jianyu.quan
 * @创建时间: 2019/7/29 0029 上午 0:01
 * @描述:
 */
public class User implements Serializable {
	private Integer id;
	private String name;
	private String password;
	private String wechat;
	private String nickname;
	private String phoneNum;
	private Boolean isAdmin;
	private String email;
	@Column(name = "enable_email_remind")
	private Integer enableEmailRemind = 0;
	private String avatar;

	public Integer getEnableEmailRemind() {
		return enableEmailRemind;
	}

	public void setEnableEmailRemind(Integer enableEmailRemind) {
		this.enableEmailRemind = enableEmailRemind;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public User() {
	}

	public Boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean admin) {
		isAdmin = admin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", wechat='" + wechat + '\'' +
				", nickname='" + nickname + '\'' +
				", phoneNum='" + phoneNum + '\'' +
				", isAdmin=" + isAdmin +
				", email='" + email + '\'' +
				", enableEmailRemind=" + enableEmailRemind +
				", avatar='" + avatar + '\'' +
				'}';
	}
}
