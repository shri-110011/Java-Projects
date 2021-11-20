package com.shri.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/home", initParams = {
		@WebInitParam(name = "name", value = "A.Siddharth"),
		@WebInitParam(name = "phone", value = "Redmi Note 5")
})
@WebInitParam(name = "name", value = "A.Siddharth")
public class MyServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		System.out.println("Inside MyServlet!");
		
		//ServletContext
		//We use ServletContext object to get the initial parameters that are common to all the servlets 
		//application wide
			
		ServletContext ctx = getServletContext();
//		
//		String name = ctx.getInitParameter("name");
//		String phone = ctx.getInitParameter("phone");
		
		//---------------------------
		
		//ServletConfig
		//We use ServletConfig object to get the initial parameters that are servlet specific.
		
		ServletConfig config = getServletConfig();
		
		String name = config.getInitParameter("name");
		String phone = config.getInitParameter("phone");
		
		PrintWriter out = res.getWriter();
		//Note: You can't write html tags inside the println() and expect those tags to work.
		out.println("Name: "+name);
		out.println("Phone: "+phone);
		
	}

}
