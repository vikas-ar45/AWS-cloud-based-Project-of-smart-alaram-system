package com.smartalarmsystem.controller;

import com.smartalarmsystem.model.AlarmRequest;
import com.smartalarmsystem.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/alarms")
@CrossOrigin(origins = "*")
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    @PostMapping("/send")
    public ResponseEntity<?> sendAlarm(@RequestBody AlarmRequest request) {
        try {
            String result = alarmService.sendAlarm(request);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", result);
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "healthy");
        response.put("service", "Smart Alarm System");
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/test")
    public ResponseEntity<?> testAlarm() {
        AlarmRequest testRequest = new AlarmRequest();
        testRequest.setMessage("This is a test alarm message");
        testRequest.setSubject("Test Alarm");
        testRequest.setSeverity("INFO");
        
        String result = alarmService.sendAlarm(testRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", result);
        return ResponseEntity.ok(response);
    }
}