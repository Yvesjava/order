package com.ziyujewelry.service;

import com.ziyujewelry.vo.Order;

import java.util.Date;
import java.util.List;

/**
 * @创建人: jianyu.quan
 * @创建时间: 2019/7/25 0025 下午 21:37
 * @描述:
 */

public interface IOrderService {

    int addOrder(Order order);

    List<Order> findOrderByUidInOneDay(Integer Uid);

	List<Order> findOrderByUidInOneMonth(Integer Uid);

	Boolean softDeleteOrderById(Integer id);

	Boolean deleteOrderById(Integer id);

	Order findOrderById(Integer id);

	List<Order> findOrderInOneDay();

	/**
	 * 查找一个时间区间内的订单列表数据
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param Uid 限定某一个用户的订单信息,userid
	 * @param isDelete 是否把删除的订单也查询出来,true是查询出来,false则反之
	 * @return List<Order>
	 */
	List<Order> findOrderInOneDayTimeSlot(Date startTime,Date endTime,Integer Uid,boolean isDelete);

}
