package com.ziyujewelry.service.impl;

import com.ziyujewelry.dao.IUserDAO;
import com.ziyujewelry.service.IUserService;
import com.ziyujewelry.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人: jianyu.quan
 * @创建时间: 2019/7/29 0029 下午 22:08
 * @描述:
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	@Override
	public User isLogin(String name, String password) {
		return userDAO.isLogin(name,password);
	}

	@Override
	public String[] findSendEmailRemindList() {
		return userDAO.findSendEmailRemindList();
	}

	@Override
	public int userRegister(User user) {
		return userDAO.userRegister(user);
	}

	@Override
	public String[] findSendEmailCallOrderList() {
		return userDAO.findSendEmailCallOrderList();
	}

	@Override
	public User findUserByName(String name) {
		return userDAO.findUserByName(name);
	}


}
