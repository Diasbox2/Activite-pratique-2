package com.example.mcpserver.tools;

import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class McpTools {

    // 1. Define the data source once so both tools use the same data
    private static final List<Employee> DB = List.of(
            new Employee("Yahya", 12300, 4),
            new Employee("Mohamed", 1030, 5),
            new Employee("Imane", 12550, 6)
    );

    @McpTool(name = "getEmployee", description = "Get information about a given employee by name")
    public Employee getEmployee(@McpArg(description = "The employee name") String name) {
        // 2. Actually SEARCH the list instead of creating a new dummy object
        return DB.stream()
                .filter(e -> e.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(new Employee("Unknown", 0, 0));
    }

    @McpTool(description = "Get All Employees")
    public List<Employee> getAllEmployees() {
        return DB;
    }
}

record Employee(String name, double salary, int seniority) {}