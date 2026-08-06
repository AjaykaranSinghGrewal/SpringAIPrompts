package com.example.springai.text;

import com.example.springai.services.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerAnyThingStreamingController {

	@Autowired
	OllamaService service;

}