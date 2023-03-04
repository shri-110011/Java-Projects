package com.shri.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/square")
public class SquareServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		System.out.println("Inside SquareServlet!");
		
//		double num = (double)req.getAttribute("sum");

		//--------------------------------
		
//		double num = Double.parseDouble(req.getParameter("sum"));
		
		//--------------------------------
		
		HttpSession session = req.getSession();
		
		double num = (double)session.getAttribute("sum");
		
		//--------------------------------
		
//		double num = 0;
//		
//		Cookie cookies[] = req.getCookies();
		
//		for(Cookie cookie: cookies) {
//			if(cookie.getName().equals("sum")) {
//				num = Double.parseDouble(cookie.getValue());
//			}
//		}
		
		//--------------------------------
		
		double square = num * num;
		
		PrintWriter out = res.getWriter();
		out.println("<html><body bgcolor='purple' style='color: white'>");
		out.println("Square of sum is: "+square);
		out.println("</body></html>");
		
	}
	
}
