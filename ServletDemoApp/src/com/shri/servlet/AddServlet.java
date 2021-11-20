package com.shri.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		System.out.println("Inside AddServlet!");
		
		double num1 =  Double.parseDouble(req.getParameter("num1"));
		double num2 = Double.parseDouble(req.getParameter("num2"));
		
		double sum = num1+num2;
		
//		PrintWriter out = res.getWriter();
//		out.println("Sum is: "+sum);
		
//		-------------------------------
		
//		req.setAttribute("sum", sum);
//		
//		//This RequestDispatcher object we can use to transfer data from one servlet to another servlet when the 
		//whole website is there in the same domain.
		
		//For use cases like Payment Gateway in a shopping website the client needs to informed that he is going to 
		//be redirected, there we can't use this RequestDispatcher object's forward().
		
//		RequestDispatcher rd = req.getRequestDispatcher("square");
//		rd.forward(req,  res);
		
//		-------------------------------
		
		HttpSession session = req.getSession();
		session.setAttribute("sum", sum);
		
//		-------------------------------
		
//		Cookie cookie = new Cookie("sum", ""+sum);
//		
//		res.addCookie(cookie);
		
//		-------------------------------
		
		res.sendRedirect("square");
		
//		-------------------------------
		
		//This technique of transferring data from one servlet to another servlet is called URL re-writing.
//		res.sendRedirect("square?sum="+sum);
		
	}
	
}
