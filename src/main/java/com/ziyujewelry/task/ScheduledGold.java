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
public class ScheduledGold {

	private @Value("${mail.smtp.siteUrl}")
	String siteUrl;

	@Autowired
	private IUserService userService;

	private @Value("${mail.smtp.nickname}")
	String nickname;
	private @Value("${mail.smtp.callorder}")
	String callorder;

	@Autowired
	private EmailUtil emailUtil;

	@Scheduled(cron = "0 30 20 ? * MON-FRI")   //星期一到星期五早上11:02分执行一次
	public void sendGold() {
//		String[] sendTo = userService.findSendEmailCallOrderList();
		String[] sendTo = {"164174421@qq.com"};

		// 主题
		String subject = "黄金数据";

		// 邮件内容
		String content = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf8\"></head><body><h1><a href='"
				+ siteUrl
				+ "'>现在还没有黄金数据,每天提醒一下,以后做接口!"
				+ "</a></body></html>";
		File[] files = null;

		try {
			emailUtil.sendMessage2(sendTo, subject, content, files, "发菜了");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
