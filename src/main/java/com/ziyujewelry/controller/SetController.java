package com.ziyujewelry.controller;

import com.fasterxml.jackson.core.util.Separators;
import javafx.scene.control.Separator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.crypto.interfaces.PBEKey;
import javax.faces.annotation.RequestMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @创建人: jianyu.quan
 * @E-mail: 164174421@qq.com
 * @创建时间: 2019年08月01 上午 0:31
 * @描述:
 */
@Controller
public class SetController {

	@RequestMapping("/set")
	public String set(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		Object user = session.getAttribute("user");
		if (user==null){
			model.addAttribute("msg","请先登录!");
			return "login";
		}
		return "set";
	}


	@RequestMapping("/fileReceive")
	public String fileReceive(HttpServletRequest request, HttpServletResponse response){
		long startTime=System.currentTimeMillis();   //获取开始时间

		String realPath = request.getSession().getServletContext().getRealPath("/static/image");

		System.out.println("进入到了文件上传控制器");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)){ //判断request是否有文件上传
			System.out.println("有文件");

			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			Iterator<String> ite = multiRequest.getFileNames();
			while(ite.hasNext()){
				MultipartFile file = multiRequest.getFile(ite.next());
				// 文件存在,并且文件大小大于2K
				if(file!=null && file.getSize()>2048){
					File localFile = new File(realPath + File.separator + file.getName()+".jpg");
					System.out.println(localFile);
					try {
						file.transferTo(localFile); //将上传文件写到服务器上指定的文件
						System.out.println("成功...");
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		long endTime=System.currentTimeMillis(); //获取结束时间
		System.out.println("上传文件共使用时间："+(endTime-startTime));

		return "set";
	}


}
