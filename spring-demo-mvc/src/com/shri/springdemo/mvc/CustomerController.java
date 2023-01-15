package com.shri.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/customer")
@Controller
public class CustomerController {
	
	/* @InitBinder annotation works as a pre-processor.
	 * It will pre-process each web request to our controller.
	 * 
	 * So what this initBinder method will do is it will process every
	 * String that comes from the form data and pre-process it.
	 * It will remove leading and trailing white spaces.
	 * And the true in the new StringTrimmerEditor() indicates that 
	 * StringTrimmerEditor will trim the string down to null if it is only 
	 * whitespaces.
	 * 
	 * And if it is false then the whitespaces will be trimmed to an empty string.
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
	}
	
	@RequestMapping("showForm")
	public String showForm(Model theModel) {
		
		theModel.addAttribute("customer", new Customer());
		
		return "customer-form";
	}
	
	@RequestMapping("/processForm")
	/* Here @Valid annotation indicates that the validation rules be applied on 
	 * this theCustomer object.
	 * 
	 * And theBindingResult object will contain the result of this validation.
	 * 
	 * When performing Spring MVC validation, the location of the BindingResult 
	 * parameter is very important. In the method signature, the BindingResult 
	 * parameter must appear immediately after the model attribute. 
	 * 
	 * If you place it in any other location, Spring MVC validation will not work 
	 * as desired. In fact, your validation rules will be ignored.
	 * 
	 */
	public String processForm(@Valid @ModelAttribute Customer theCustomer, BindingResult theBindingResult) {
		
		System.out.println("lastName1: |" + theCustomer.getLastName() + "|");
		
		System.out.println("BindingResult: "+theBindingResult);
		System.out.println("\n\n");
		
		if(theBindingResult.hasErrors()) {
			return "customer-form";
		}
		else {
			return "customer-confirmation";
		}
	}

}
