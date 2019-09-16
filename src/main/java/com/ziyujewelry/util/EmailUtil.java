package com.ziyujewelry.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @创建人: jianyu.quan
 * @E-mail: 164174421@qq.com
 * @创建时间: 2019年08月04 上午 3:48
 * @描述:
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@PropertySource(value = "classpath:mail.properties",encoding = "UTF-8",ignoreResourceNotFound = true)
public class EmailUtil {

	@Autowired
	private JavaMailSender javaMailSender;

	private @Value("${mail.smtp.host}")	String host;
	private @Value("${mail.smtp.username}") String username;
	private @Value("${mail.smtp.nickname}") String nickname;
	private @Value("${mail.smtp.password}") String password;
	private @Value("${mail.smtp.defaultEncoding}") String defaultEncoding;
	private @Value("${mail.smtp.auth}") String auth;
	private @Value("${mail.smtp.timeout}") Integer timeout;

	/**
	 *
	 * @param to {"123@123.com","456@123.com"} 可以以这种形式传入
	 * @param subject 邮件主题
	 * @param content 邮件内容,可以是html代码
	 * @param attachment 附件,可以不传
	 * @throws MessagingException 出错的exception
	 */
//	public void sendMessage(String[] to, String subject , String content,File[] attachment) throws Exception {
//		MimeMessage msg = javaMailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
//		helper.setTo(to);
//
//		helper.setFrom(new InternetAddress(this.username,this.nickname,"UTF-8"));
//
//		helper.setSubject(subject);
//		helper.setText(content, true);
//
//		// add the rar file
////		FileSystemResource rarfile = new FileSystemResource(new File("D:/workspace/netbar-dw/src/netbar/dw/util/SendMail.java"));
////		helper.addAttachment("SendMail.java", rarfile);
//
//		javaMailSender.send(msg);
//	}

	/**
	 *
	 * @param to {"123@123.com","456@123.com"} 可以以这种形式传入
	 * @param subject 邮件主题
	 * @param content 邮件内容,可以是html代码
	 * @param attachment 附件,可以不传
	 * @throws MessagingException 出错的exception
	 */
	public void sendMessage2(String[] to, String subject , String content,File[] attachment,String title) throws Exception {
		//创建邮件对象
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper mMessageHelper;

		mMessageHelper=new MimeMessageHelper(message,true);

//		mMessageHelper.setFrom(from);//发件人邮箱
		mMessageHelper.setFrom(new InternetAddress(this.username,title,"UTF-8"));


		mMessageHelper.setTo(to);//收件人邮箱
		mMessageHelper.setSubject(subject);//邮件的主题
		mMessageHelper.setText(content,true);//邮件的文本内容，true表示文本以html格式打开

		javaMailSender.send(message);//发送邮件
	}



}
