package com.oliwa.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SampleController {
	// requestmapping domyslnie nie specyfikuje typu czy get czy post etc
	// (obsluguje wszystko z http)
	@RequestMapping("/hello")
	public String sayHello(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "hello";
	}

	@RequestMapping("/")
	public String blabla() {
		return "index";
	}
}
