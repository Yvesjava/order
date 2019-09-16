package com.ziyujewelry.service;

import com.ziyujewelry.vo.User;

import java.util.List;

/**
 * @创建人: jianyu.quan
 * @创建时间: 2019/7/29 0029 下午 22:07
 * @描述:
 */
public interface IUserService {

	User isLogin(String name, String password);

	String[] findSendEmailRemindList();

	int userRegister(User user);

	String[] findSendEmailCallOrderList();

	User findUserByName(String name);
}
