package com.example.springai.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OllamaService {

    //chatclient can be used with any model. spring will figure out which model we're calling from pom file
	private ChatClient chatClient;

    //constructor
    //advisor (MessageChatMemoryAdvisor) keep previous prompts by user in memory
    //chatMemory Bean will be created in Config.java class & injected into below constructor
    public OllamaService(ChatClient.Builder builder, ChatMemory chatMemory) {
        chatClient = builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()).build();
    }

    public String getTravelGuidance(String conversationId, String city, String month, String language, String budget) {
        PromptTemplate promptTemplate = new PromptTemplate("Welcome to the {city} travel guide!\n" +
                "If you're visiting in {month}, here's what you can do:\n" +
                "1. Must-visit attractions.\n" +
                "2. Local cuisine you must try.\n" +
                "3. Useful phrases in {language}.\n" +
                "4. Tips for traveling on a {budget} budget.\n" +
                "Enjoy your trip!");
        Prompt prompt = promptTemplate.create(Map.of("city", city, "month", month, "language", language, "budget", budget));

        return chatClient.prompt(prompt)
                // REQUIRED: You must pass the conversation ID parameter at execution time or else spring will throw a runtime error
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call().chatResponse().getResult().getOutput().getText();
    }
}
