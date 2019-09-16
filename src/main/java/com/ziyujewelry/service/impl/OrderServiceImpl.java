package com.ziyujewelry.service.impl;

import com.ziyujewelry.dao.IOrderDAO;
import com.ziyujewelry.service.IOrderService;
import com.ziyujewelry.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @创建人: jianyu.quan
 * @创建时间: 2019/7/25 0025 下午 21:39
 * @描述:
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderDAO orderDAO;

	@Override
	public int addOrder(Order order) {
		return orderDAO.addOrder(order);
	}

	@Override
	public List<Order> findOrderByUidInOneDay(Integer Uid) {
		return orderDAO.findOrderByUidInOneDay(Uid);
	}

	@Override
	public List<Order> findOrderByUidInOneMonth(Integer Uid) {
		return orderDAO.findOrderByUidInOneMonth(Uid);
	}

	@Override
	public Boolean softDeleteOrderById(Integer id) {
		return orderDAO.softDeleteOrderById(id);
	}

	@Override
	public Boolean deleteOrderById(Integer id) {
		return orderDAO.deleteOrderById(id);
	}

	@Override
	public Order findOrderById(Integer id) {
		return orderDAO.findOrderById(id);
	}

	@Override
	public List<Order> findOrderInOneDay() {
		return orderDAO.findOrderInOneDay();
	}

	@Override
	public List<Order> findOrderInOneDayTimeSlot(Date startTime, Date endTime,Integer Uid,boolean isDelete) {
		return orderDAO.findOrderInOneDayTimeSlot(startTime,endTime,Uid,isDelete);
	}


}
