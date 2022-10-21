package com.shri.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@RequestMapping("/showForm")
	/* Here before we show the form we are adding a model attribute
	 * because we want to make use of data binding.
	 * 
	 */
	public String showForm(Model theModel) {
		
		//create a student object
		Student theStudent = new Student();
		
		//add that student object to the model
		theModel.addAttribute("student", theStudent);
		
		return "student-form";
	}
	
	@RequestMapping("/processForm")
	/* Here we make use of @ModelAttribute("student") to read the form data
	 * from the model. So @ModelAttribute basically binds the form data to the
	 * object.
	 * 
	 */
	public String processForm(@ModelAttribute("student") Student theStudent) {
		
		//log the input data
		System.out.println("theStudent: "+theStudent.getFirstName()+" "+theStudent.getLastName());
		return "student-confirmation";
	}
}
