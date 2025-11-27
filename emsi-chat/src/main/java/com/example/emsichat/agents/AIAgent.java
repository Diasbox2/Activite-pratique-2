package com.example.emsichat.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AIAgent {
    private final ChatClient chatClient;

    public AIAgent(ChatClient.Builder builder,
                   ChatMemory memory, ToolCallbackProvider tools) {

        // Debugging: Print available tools to console
        // We print the whole definition to avoid method name issues (getName vs name)
        Arrays.stream(tools.getToolCallbacks()).forEach(toolCallback -> {
            System.out.println("Tool loaded: " + toolCallback.getToolDefinition());
        });

        this.chatClient = builder
                .defaultSystem("""
                    You are an assistant for the EMSI employee management system.
                    
                    RULES:
                    1. If you are asked to get data (like employees), call the appropriate tool.
                    2. Once you receive the data, SUMMARIZE it in natural language.
                    3. Do NOT try to use a function named 'print', 'echo', or 'console'. 
                    4. Do NOT output raw JSON unless explicitly asked.
                    5. If you don't know the answer, say "I don't know".
                    """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .defaultToolCallbacks(tools)
                .build();
    }

    public String askAgent(String query) {
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }
}