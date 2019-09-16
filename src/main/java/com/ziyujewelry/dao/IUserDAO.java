package com.ziyujewelry.dao;

import com.ziyujewelry.vo.Order;
import com.ziyujewelry.vo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @创建人: jianyu.quan
 * @创建时间: 2019/7/25 0025 上午 1:07
 * @描述:
 */
@Repository
public interface IUserDAO {

    @Select({" select id,name,nickname,wechat,phoneNum,isAdmin,email from user where name=#{name} and password=#{password} "})
    User isLogin(@Param("name")String name,@Param("password")String password);

    @Select({" select * from user where id=#{id} "})
    User findUserById(Integer id);

	@Select({" select * from user where name=#{name} "})
    User findUserByName(String name);

	/**
	 *
	 * @return 返回需要邮件提醒的用户列表
	 */
	@Select({" select email from user where enable_email_remind=1 and email<>'' "})
	String[] findSendEmailRemindList();

	/**
	 *
	 * @return 返回管理员的邮件列表,用于提醒点餐
	 */
	@Select({" select email from user where enable_email_remind=1 and email<>'' and isAdmin=1 "})
	String[] findSendEmailCallOrderList();

	@Insert({" insert into user(name, password, wechat, nickname,phoneNum,email,enable_email_remind) values(#{name}, #{password}, #{wechat}, #{nickname}, #{phoneNum}, #{email}, #{enableEmailRemind}) "})
	@Options(useGeneratedKeys = true, keyProperty = "id" ,keyColumn = "id")
	int userRegister(User user);

}
