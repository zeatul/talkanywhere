package com.taw.user.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hawk.utility.DateTools;

@Controller
public class HelloWorldController {
	
	private static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	


	public static class Page {
		public Integer getIndex() {
			return index;
		}

		public void setIndex(Integer index) {
			this.index = index;
		}

		public Integer getSize() {
			return size;
		}

		public void setSize(Integer size) {
			this.size = size;
		}

		private Integer index;
		private Integer size;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/hello.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String home(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		request.setAttribute("serverTime", formattedDate );
		
		return "home";
	}

	@RequestMapping(value = "/webapp/root.do", method = RequestMethod.GET)
	public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");

		sb.append("<head>");
		sb.append("<title>Home</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h1>");
		sb.append("Hello world!");		
		sb.append("</h1>");
		sb.append("<h2>");
		String webappRoot = System.getProperty("webapp.root");
		sb.append("webapp.root="+webappRoot);
		sb.append("</h2>");
		sb.append("<P>  The time on the server is "+DateTools.now(null)+". </P>");
		
		
		sb.append("<P>  userManager.test() is "+""+". </P>");
		sb.append("</body>");
		sb.append("</html>");
		

		response.getWriter().print(sb.toString());
	}
	
	@RequestMapping(value = "/helloJump.do", method = RequestMethod.GET)
	public void helloJump(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.sendRedirect("http://www.sina.com.cn");
	}

	@RequestMapping(value = "/hello/cache.do", method = RequestMethod.GET)
	public void clearCache(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		StringBuilder sb = new StringBuilder();

		response.getWriter().print(sb.toString());

	}

	@RequestMapping(value = "/hello/get.do", method = RequestMethod.GET)
	public void helloWorldGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Cache-Control", "no-cache");		
		

		response.getWriter().print("hello/get");
	}

	@RequestMapping(value = "/hello/post.do", method = RequestMethod.POST)
	public void helloWorldPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Cache-Control", "no-cache");

		String output =" DESTools.decrypt(input, key)";

		System.out.println("output=" + output);
		response.getWriter().print("hello,post");
	}

	@RequestMapping(value = "/hello/delete.do", method = RequestMethod.GET)
	public void helloWorldDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-cache");

		response.getWriter().print("Hello Delete,succeeded");
	}

	@RequestMapping(value = "/hello/put.do", method = RequestMethod.GET)
	public void helloWorldPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print("Hello Put,Succeed");
	}
	
	@RequestMapping(value = "/hello/test.do", method = RequestMethod.GET)
	public void helloWorldTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().print("digitId=" +"userManager.createUser().getDigitId()");
	}

}
