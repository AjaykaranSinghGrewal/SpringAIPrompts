package com.example.springai.config;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration annotation makes spring pick this class at runtime
@Configuration
public class Config {

    //MessageWindowChatMemory keeps record of questions asked to the LLM model. this is part of Spring AI Advisors.
    @Bean
    ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder().build();
    }
}
