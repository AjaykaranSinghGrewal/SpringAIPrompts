package com.example.springai.tools;

import com.example.springai.services.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherAgentController {

	@Autowired
	private OllamaService service;

	@GetMapping("/showWeatherAgent")
	public String showWeatherAgent() {
		return "weatherTool";
	}

	@PostMapping("/weatherAgent")
	public String weatherAgent(@RequestParam("query") String query, Model model) {
		return "weatherTool";
	}
}