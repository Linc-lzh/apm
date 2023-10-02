package com.bit.monitor.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class StatisticsAction
 */
@WebServlet("/statis")
public class StatisticsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EsServiceImpl esService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticsAction() {
        super();
        // TODO Auto-generated constructor stub
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
		String format = request.getParameter("format");
		Long begin = request.getParameter("begin") == null ? null : Long.parseLong(request.getParameter("begin"));
		Long end = request.getParameter("end") == null ? null : Long.parseLong(request.getParameter("end"));
		String serviceName = request.getParameter("service");
		List<StatisticsVo> result = esService.getStatistics(begin, end, serviceName);
		if ("json".equals(format)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json; charset=utf-8");
			response.getWriter().write(JSON.toJSONString(result));
		} else  {
			request.setAttribute("statis", result);
			java.util.List list = (java.util.List) request.getAttribute("statis");
			request.getRequestDispatcher("/MonitorView.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
