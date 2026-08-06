package com.example.springai.text.prompttemplate;

import com.example.springai.services.OllamaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class 	TravelGuideController {
	@Autowired
	private OllamaService service;

	@GetMapping("/showTravelGuide")
	public String showChatPage() {
		return "travelGuide";
	}

	@PostMapping("/travelGuide")
	public String getChatResponse(@RequestParam("city") String city, @RequestParam("month") String month,
                                  @RequestParam("language") String language, @RequestParam("budget") String budget, HttpSession session, Model model) {

		// Use the HTTP session ID as the conversation ID for chat memory
		String conversationId = session.getId();

		String response = service.getTravelGuidance(conversationId, city, month, language, budget);
		model.addAttribute("response", response);;
		model.addAttribute("city", city);
		return "travelGuide";
	}

}
