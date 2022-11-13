package com.shri.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/add")
public class DemoServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String userName = req.getParameter("userName");
		System.out.println("Inside DemoServlet doGet() ...");
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html><html><body>");
		out.println("<h3>");
		out.println("Hi "+userName);
		out.println("</h3></body></html>");
	}

}
