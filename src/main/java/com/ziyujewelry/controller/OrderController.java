package com.ziyujewelry.controller;

import com.ziyujewelry.service.IOrderService;
import com.ziyujewelry.service.IUserService;
import com.ziyujewelry.util.DateTimeUtil;
import com.ziyujewelry.util.EmailUtil;
import com.ziyujewelry.util.IsHoliday;
import com.ziyujewelry.vo.Order;
import com.ziyujewelry.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @创建人: jianyu.quan
 * @创建时间: 2019/7/25 0025 上午 0:39
 * @描述:
 */
@Controller
public class OrderController {

	private @Value("${mail.smtp.siteUrl}") String siteUrl;

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private DateTimeUtil dateTimeUtil;

	@Autowired
	private IUserService userService;

	@Autowired
	private IOrderService orderService;

    @RequestMapping(value = "/addOrder",method = RequestMethod.POST)
    public String addOrder(Order order, Model model, HttpServletRequest request){
	    System.out.println("进入后台addOrder");
	    HttpSession session = request.getSession();
	    Object user = session.getAttribute("user");
	    if (user == null){
	    	model.addAttribute("msg","sorry!请登录后再点餐");
		    return "login";
	    }
	    if (order == null){
	        model.addAttribute("msg","请提供完整的参数");
	        return "index";
        }

	    order.setCreatetime(new Date());
	    order.setUser((User) user);
	    System.out.println(order);
	    orderService.addOrder(order);

        // 重定向到首页
        return "redirect:/";
    }

	@RequestMapping("/userLogin")
	public String userLogin(String username, String password, HttpServletRequest request,Model model){
		if (username == null || password==null){
			return "login";
		}
		User user = userService.isLogin(username, password);
		if (user!=null){
			HttpSession session = request.getSession();
			session.setAttribute("user",user);
			System.out.println("login...");
			System.out.println(user);
			return "redirect:/";
		}else {
			if (username!=null && password!=null){
				model.addAttribute("msg","登录失败!请重试!");
			}
			return "login";
		}
	}


	@RequestMapping(value = "/softDelete",method = RequestMethod.GET)
	public String softDelete(Integer id,Integer uId,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user==null){
			return "login";
		}

		Order order = orderService.findOrderById(id);
		if (order!=null){
			orderService.softDeleteOrderById(id);
		}

		return "redirect:/";
	}


	@RequestMapping("/")
	public String index(HttpServletRequest request,Model model) throws Exception {
		System.out.println("index ....");

		// 进入首页,把user,List<Order>查询出来
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// 存在用户把Order查询出来
		if (user!=null){
			System.out.println(user);
			// 当天的订单
			List<Order> orders;

			Calendar ca = Calendar.getInstance();
			Map<String, Date> dateInterval = null;
			if (ca.get(Calendar.HOUR_OF_DAY)<17){
				dateInterval = dateTimeUtil.getDateInterval(new Date(), 9, 17);
			}else {
				dateInterval = dateTimeUtil.getDateInterval(new Date(), 17, 23);
			}
			Date startTime = dateInterval.get("start");
			Date endTime = dateInterval.get("end");

			boolean isAll = true;

			if (isAll) {
				orders = orderService.findOrderInOneDayTimeSlot(startTime, endTime, 0,false);
			}else {
				if (user.getAdmin()) {
					//findOrderInOneDayTimeSlot(Date startTime,Date endTime,Integer Uid);
					orders = orderService.findOrderInOneDayTimeSlot(startTime, endTime, 0,false);
				} else {
					orders = orderService.findOrderInOneDayTimeSlot(startTime, endTime, user.getId(),false);
				}
			}
			System.out.println(orders);
			model.addAttribute("orders",orders);
			// 当天的订单结束

			// 订单数量统计 和 订单金额统计
			Double totalMoney = 0.0;
			for (Order order : orders) {
				totalMoney += order.getMoney();
			}
			Integer orderCount = orders.size();
			model.addAttribute("orderCount",orderCount);
			model.addAttribute("totalMoney",totalMoney);


			// 当月的订单
//			List<Order> ordersMonth = orderService.findOrderByUidInOneMonth(user.getId());
//			System.out.println(ordersMonth);
//			model.addAttribute("ordersMonth",ordersMonth);
		}

		model.addAttribute("stamp",new Date().getTime()/1000);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = df.parse("2019-08-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar ca = Calendar.getInstance();
//		ca.setTime(d);//设置当前时间
		IsHoliday ct = new IsHoliday();
		ca.get(Calendar.HOUR_OF_DAY);

		try {
			// 是节假日 或者  !(时间>9点 && <11点)
			if (ct.checkHoliday(ca) || !((ca.get(Calendar.HOUR_OF_DAY)>=9 && ca.get(Calendar.HOUR_OF_DAY)<11) || (ca.get(Calendar.HOUR_OF_DAY)>=17 && ca.get(Calendar.HOUR_OF_DAY)<18))){
				model.addAttribute("msg","亲爱的同事们!当前时间不可以点餐哦!");
				model.addAttribute("orderTime",false);
				return "index";
			}
			// 可以点餐
			model.addAttribute("orderTime",true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "index";










//		System.out.println("jinlaile ....");
//
//
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Date d = null;
//		try {
//			d = df.parse("2019-08-01");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		Calendar ca = Calendar.getInstance();
////		ca.setTime(d);//设置当前时间
//		IsHoliday ct = new IsHoliday();
//		ca.get(Calendar.HOUR_OF_DAY);
//		try {
//			// 是节假日 或者  时间<9点  >11点
//			if (ct.checkHoliday(ca) || (ca.get(Calendar.HOUR_OF_DAY)<9 && ca.get(Calendar.HOUR_OF_DAY)>11)){
//				model.addAttribute("msg","亲爱的同事们!当前时间不可以点餐哦!");
//				model.addAttribute("orderTime",false);
//				return "index";
//			}
//
//			System.out.println(ca.getTime());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//
//		// 进入首页,把user,List<Order>查询出来
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("user");
//		// 存在用户把Order查询出来
//		if (user!=null){
//			System.out.println(user);
//			// 当天的订单
//			List<Order> orders;
//			if (user.getAdmin()){
//				orders = orderService.findOrderInOneDay();
//			}else {
//				orders = orderService.findOrderByUidInOneDay(user.getId());
//			}
//			System.out.println(orders);
//			// 当月的订单
//			List<Order> ordersMonth = orderService.findOrderByUidInOneMonth(user.getId());
//			System.out.println(ordersMonth);
//			model.addAttribute("orders",orders);
//			model.addAttribute("ordersMonth",ordersMonth);
//		}
//
//		return "index";
	}

	@RequestMapping("/testEmail")
	public String testEmail(HttpServletRequest request) throws Exception{
//		String[] sendTo = userService.findSendEmailRemindList();
		String[] sendTo = {"164174421@qq.com"};

		// 主题
		String subject = "点击发送测试!";

		// 邮件内容
		String content = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf8\"></head><body><h1><a href='"+siteUrl+"'>测试玩就睡觉!"
				+ "</a></body></html>";
		File[] files = null;

		try {
			emailUtil.sendMessage2(sendTo,subject,content,files,"首页测试");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}


}
