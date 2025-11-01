package com.SaaS_Based_Customer_Relationship_Management.CRM.mcp.controller;

import com.SaaS_Based_Customer_Relationship_Management.CRM.mcp.handler.CreateCustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mcp")
public class MCPTestController {

    @Autowired
    private CreateCustomerHandler handler;

    @PostMapping("/create-customer")
    public String createCustomer(@RequestBody Map<String, String> payload) {
        return handler.handle(
            payload.get("name"),
            payload.get("email"),
            payload.get("phone")
        );
    }
}
