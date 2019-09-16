package com.ziyujewelry.test;

import com.ziyujewelry.service.IOrderService;
import com.ziyujewelry.service.IUserService;
import com.ziyujewelry.util.EmailUtil;
import com.ziyujewelry.util.IsHoliday;
import com.ziyujewelry.vo.Order;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;


/**
 * @创建人: jianyu.quan
 * @创建时间: 2019/7/25 0025 下午 23:43
 * @描述:
 */

public class TestOrderService {

	static private IOrderService orderService;
	static private IUserService userService;
	static private JavaMailSender javaMailSender;
	static private EmailUtil emailUtil;

	@BeforeClass
	public static void before() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		orderService = (IOrderService) ctx.getBean("orderService");
		userService = (IUserService) ctx.getBean("userService");
		emailUtil = (EmailUtil) ctx.getBean("emailUtil");
//		javaMailSender = (JavaMailSender) ctx.getBean("javaMailSender");

//		ApplicationContext context = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/applicationContext.xml");
//		new ClassPathXmlApplicationContext("applicationContext.xml");// 从classpath中加载
//		new FileSystemXmlApplicationContext("classpath:地址");// 没有classpath表示当前目录

	}

	@Test
	public void testAdd() {

		Date date = new Date();

		Order order = new Order();
//		order.setuId(1);
		order.setDish("辣椒炒肉");
		order.setMoney(33.0);
        order.setCreatetime(date);

//        orderService.addOrder(order);
		System.out.println(order);

	}

	@Test
	public void testUserLogin() throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = df.parse("2019-08-01");
		Calendar ca = Calendar.getInstance();
//		ca.setTime(d);//设置当前时间

		IsHoliday ct = new IsHoliday();
		// 手动添加节假日列表
//		ct.initHolidayList("2019-08-06");//初始节假日
//		ct.initHolidayList("2019-08-07");//初始节假日
//		ct.initHolidayList("2019-08-01");//初始节假日

		System.out.println(ct.checkHoliday(ca));
		System.out.println(ca.get(Calendar.HOUR_OF_DAY));

//		Calendar c = ct.addDateByWorkDay(ca,5);
//		System.out.println(df.format(c.getTime()));


//		String name = "qjy";
//		String passwrod = "kmd2019";
//		User user = UserService.isLogin(name, passwrod);
//		System.out.println(user);
	}

	@Test
	public void testfindSendEmailRemindList(){
		String[] remindList = userService.findSendEmailRemindList();

		for (String s : remindList) {
			System.out.println(s);
		}

	}


	@Test
	public void testSendMail() throws Exception {

		MimeMessage mMessage=javaMailSender.createMimeMessage();//创建邮件对象
		MimeMessageHelper mMessageHelper;
		Properties prop = new Properties();
//		String from;

		//从配置文件中拿到发件人邮箱地址
		prop.load(new InputStreamReader(getClass().getResourceAsStream("/mail.properties"),"UTF-8"));

		mMessageHelper=new MimeMessageHelper(mMessage,true);

//		mMessageHelper.setFrom(from);//发件人邮箱
		mMessageHelper.setFrom(new InternetAddress(prop.get("mail.smtp.username")+"",prop.get("mail.smtp.nickname")+"","UTF-8"));


		mMessageHelper.setTo("164174421@qq.com");//收件人邮箱
		mMessageHelper.setSubject("Spring的邮件发送");//邮件的主题
		mMessageHelper.setText("<p>这是使用spring的邮件功能发送的一封邮件</p><br/>" +
				"<a href='https://www.baidu.com'>订餐</a><br/>" +
				"<img src='cid:fengye'>",true);//邮件的文本内容，true表示文本以html格式打开


		//在邮件中添加一张图片
//		File file=new File("H:/照片/iphone6/DCIM/100APPLE/IMG_0786.JPG");
//		FileSystemResource resource=new FileSystemResource(file);
//		mMessageHelper.addInline("IMG_0786", resource);//这里指定一个id,在上面引用
//		mMessageHelper.addAttachment("IMG_0786.png", resource);//在邮件中添加一个附件
		javaMailSender.send(mMessage);//发送邮件

	}

	private @Value("${mail.smtp.siteUrl}")	String siteUrl;


	@Test
	public void test1() throws Exception {
		String[] sendTo = {"164174421@qq.com"};

		// 主题
		String subject = "订餐啦!";

		// 邮件内容
		String content = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf8\"></head><body><h1><a href='"+siteUrl+"'>点我点餐!"
				+ "</a></body></html>";
		File[] files = null;

		try {
			emailUtil.sendMessage2(sendTo,subject,content,files,"hah");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
