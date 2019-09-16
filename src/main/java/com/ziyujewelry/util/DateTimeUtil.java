package com.ziyujewelry.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人: quanjianyu
 * @E-mail: 164174421@qq.com
 * @创建时间: 2019年08月19 下午 21:02
 * @描述:
 */
@Component
public class DateTimeUtil {

	public DateTimeUtil() {
	}

	public Map<String,Date> getDateInterval(Date date, Integer start, Integer end) throws Exception{
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat formater2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date startTime = formater2.parse(formater.format(date)+ " "+start+":00:00");
		Date endTime = formater2.parse(formater.format(date)+ " "+end+":00:00");
		HashMap<String,Date> dateMap = new HashMap<>();
		dateMap.put("start",startTime);
		dateMap.put("end",endTime);
		return dateMap;
	}

}
