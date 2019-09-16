package com.ziyujewelry.controller;

import com.ziyujewelry.service.IUserService;
import com.ziyujewelry.vo.Order;
import com.ziyujewelry.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @创建人: jianyu.quan
 * @创建时间: 2019/7/25 0025 上午 0:39
 * @描述:
 */
@Controller
public class UserController {

	@Autowired
	private IUserService userService;


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String addOrder(HttpServletRequest request){
	    System.out.println("进入后台logout....");
	    HttpSession session = request.getSession();
	    Object user = session.getAttribute("user");
	    if (user == null){
//	    	model.addAttribute("msg","sorry!请登录后再点餐");
		    return "index";
	    }
	    session.removeAttribute("user");
        return "redirect:/";
    }

    @RequestMapping("/userRegister")
	public String userRegister(User user,Model model){
	    User tblUser = userService.findUserByName(user.getName());
	    if (tblUser!=null){
		    model.addAttribute("msg","该用户已经存在,请更换后重试");
		    return "redirect:/userLogin";
	    }

	    if (user.getName().trim().equals("")){
	    	model.addAttribute("msg","用户名不合法,请重试");
		    return "redirect:/userLogin";
	    }

	    if (user.getPassword().trim().equals("")){
	    	model.addAttribute("msg","密码不合法,请重试");
		    return "redirect:/userLogin";
	    }

	    if (user.getNickname().trim().equals("")){
	    	model.addAttribute("msg","请填写昵称,请重试");
		    return "redirect:/userLogin";
	    }

	    int i = userService.userRegister(user);
	    if (i>0 && user.getId()>0){
		    // 插入成功
		    model.addAttribute("msg","注册成功,请登录");
		    return "redirect:/userLogin";
	    }else {
		    model.addAttribute("msg","注册失败,请联系管理员");
		    return "redirect:/userLogin";
	    }
	}



}
