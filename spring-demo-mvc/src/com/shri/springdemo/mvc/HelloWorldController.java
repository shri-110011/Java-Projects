package com.shri.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
/* Here we have the controller level request mapping and all the
 * mappings on the methods within this controller are relative to it.
 */
@RequestMapping("/hello")
public class HelloWorldController {

	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}
	
	/* Add new controller method to read form data
	 * and add data to the model.
	 * 
	 * When the model comes into our method as a parameter initially 
	 * it is empty.
	 */
	
	@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		
		//read the request parameter from the HTML form
		String theName = request.getParameter("studentName");
		
		//convert the data to all caps
		theName = theName.toUpperCase();
		
		//create the message
		String result = "Yo! "+theName; 
		
		//add message to the model
		model.addAttribute("message", result);
		
		return "helloworld";
	}
	
	/* @RequestParam annotation will help to read the form data and automatically
	 * bind it to the parameter coming into our method.
	 * Here @RequestParam("studentName") would bind the form field having name
	 * 'studentName' to the parameter 'theName'.
	 * 
	 */
	@RequestMapping("/processFormVersionThree")
	public String processFormVersionTree(
			@RequestParam("studentName") String theName, 
			Model model) {
		
		//convert the data to all caps
		theName = theName.toUpperCase();
		
		//create the message
		String result = "Good day my friend! "+theName;
		
		//add message to the model
		model.addAttribute("message", result);
		
		return "helloworld";
	}
	
}
