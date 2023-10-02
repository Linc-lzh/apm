package com.bit.monitor.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class MonitorAction
 */
@WebServlet("/MonitorAction")
public class MonitorAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EsServiceImpl esService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MonitorAction() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		esService = context.getBean(EsServiceImpl.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data = 	new String(request.getParameter("data").getBytes("ISO-8859-1"),"utf-8");
		esService.putData(data);
		response.getWriter().append("OK!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	

}
