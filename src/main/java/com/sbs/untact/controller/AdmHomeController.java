package com.sbs.untact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdmHomeController {

	@RequestMapping("/adm/home/board")
	@ResponseBody
	public String showMain() {

		System.out.println("안녕");

		return "안녕하세요";

	}
}
