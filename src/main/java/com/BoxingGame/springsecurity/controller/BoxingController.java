package com.BoxingGame.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BoxingGame.springsecurity.entity.Boxer;
import com.BoxingGame.springsecurity.service.BoxerService;

@Controller
public class BoxingController {

	
	@GetMapping("/")
	public String showHome() {
		
		return "home";
	}
	
	@GetMapping("/boxers")
	public String showBoxers(){
		
		return "boxers";
	}
	
	// inject boxer service
	@Autowired
	private BoxerService boxerService;
		
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
			
		//get boxers from the dao
		List<Boxer> theBoxers = boxerService.getBoxers();
			
		//add the boxers to the model
		theModel.addAttribute("Boxers", theBoxers);
			
		return "list-boxers";
	}
		
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
			
		// create model attribute to bind form data
		Boxer theBoxer = new Boxer();
			
		theModel.addAttribute("boxer", theBoxer);
			
		return "boxer-form";
	}
		
	@PostMapping("/saveBoxer")
	public String saveBoxer(@ModelAttribute("boxer") Boxer theBoxer) {
			
		// save the boxer using our service
		boxerService.saveBoxer(theBoxer);
			
		return "redirect:/list";
	}
		
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("boxerId") int theId, Model theModel) {
		
		//get the boxer from the service
		Boxer theBoxer = boxerService.getBoxer(theId);
			
		// set the customer as a  model attribute to pre-populate the form
		theModel.addAttribute("boxer", theBoxer);
			
		// send over to our form
		return "boxer-form";
	}
		
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("boxerId") int theId) {
			
		//delete the boxer
		boxerService.deleteBoxer(theId);
			
		return "redirect:/list";
	}
}
