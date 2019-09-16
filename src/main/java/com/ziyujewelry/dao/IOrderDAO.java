package com.ziyujewelry.dao;

import com.ziyujewelry.vo.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @创建人: jianyu.quan
 * @创建时间: 2019/7/25 0025 上午 1:07
 * @描述:
 */
@Repository("orderDAO")
public interface IOrderDAO {

	/**
	 * 添加一条订单记录
	 * @param order 传入order对象
	 * @return 返回受影响条数,同样把插入的数据,如增加的id返回到order对象中,可以直接拿到id
	 */
    @Insert({"insert into `order`(u_id,dish,money,createtime) values(#{user.id},#{dish},#{money},#{createtime})"})
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int addOrder(Order order);

	/**
	 * 查询当日的订单
	 * @param uId 传入用户的id
	 * @return 返回list集合,集合中是Order
	 */
	@Select({" select * from `order` where datediff(createtime,now())=0 and u_id=#{uId} "})
    List<Order> findOrderByUidInOneDay(Integer uId);
	/**
	 * 查询当日的订单
	 * @return 返回list集合,集合中是Order
	 */
	@Select({" select * from `order` where datediff(createtime,now())=0 "})
	@Results({
			@Result(column = "u_id",property="user",one=@One(select="com.ziyujewelry.dao.IUserDAO.findUserById"))
	})
	List<Order> findOrderInOneDay();

	/**
	 * 查询一个时间段的订单
	 * @return 订单list
	 */
//	@Select({" select * from `order` where createtime >= #{startTime,jdbcType=TIMESTAMP} and createtime < #{endTime,jdbcType=TIMESTAMP} "})
	@Select({
			"<script>",
			" select ",
			" * ",
			" from `order` ",
			" where createtime &gt;= #{startTime,jdbcType=TIMESTAMP} ",
			" and createtime &lt; #{endTime,jdbcType=TIMESTAMP} ",
			" <if test='id &gt; 0'> and u_id=#{id} </if> ",
			" <when test='!isDelete'> and deleted=0 </when> ",
			" <when test='isDelete'> and deleted=1 </when> ",
			" </script>",
	})
	@Results({
			@Result(column = "u_id",property="user",one=@One(select="com.ziyujewelry.dao.IUserDAO.findUserById"))
	})
	List<Order> findOrderInOneDayTimeSlot(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("id") Integer id,@Param("isDelete") boolean isDelete);

	/**
	 * 查询当月的订单
	 * @param uId 传入用户的id
	 * @return 返回list集合,集合中是Order
	 */
	@Select({" select * from `order` where DATE_FORMAT( createtime, '%Y%m' ) = DATE_FORMAT( CURDATE() , '%Y%m' ) and u_id=#{uId} "})
    List<Order> findOrderByUidInOneMonth(Integer uId);

	/**
	 * 根据订单id删除相应的订单记录
	 * @param id 传入的orderId
	 * @return 返回结果
	 */
	@Delete({" delete from `order` where id=#{id} "})
	Boolean deleteOrderById(Integer id);


	/**
	 * 根据订单id实现软删除,修改该订单的deleted状态值,改为1
	 * @param id orderId
	 * @return 返回修改的状态
	 */
	@Update({" update `order` set deleted = 1 where id = #{id} "})
	Boolean softDeleteOrderById(Integer id);

	@Select({" select * from `order` where id = #{id} "})
	Order findOrderById(Integer id);


}
