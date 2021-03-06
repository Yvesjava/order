package com.ziyujewelry.task;

import com.ziyujewelry.service.IUserService;
import com.ziyujewelry.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;


/**
 * @创建人: jianyu.quan
 * @E-mail: 164174421@qq.com
 * @创建时间: 2019年08月04 上午 2:55
 * @描述:
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TestScheduled2 {

	private @Value("${mail.smtp.siteUrl}")	String siteUrl;

	@Autowired
	private IUserService userService;

	private @Value("${mail.smtp.nickname}") String nickname;
	private @Value("${mail.smtp.callorder}") String callorder;

	@Autowired
	private EmailUtil emailUtil;

	@Scheduled(cron="0 15 10 ? * MON-FRI")   //星期一到星期五早上10:15分执行一次
	public void myTest(){
		String[] sendTo = userService.findSendEmailRemindList();

		// 主题
		String subject = "订餐啦!";

		// 邮件内容
		String content = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf8\"></head><body><h1><a href='"+siteUrl+"'>点我点餐!"
				+ "</a></body></html>";
		File[] files = null;

		try {
			emailUtil.sendMessage2(sendTo,subject,content,files,this.nickname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
