package com.smartalarmsystem.service;

import com.smartalarmsystem.model.AlarmRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AlarmService {

    @Autowired
    private SNSClient snsClient;

    private static final DateTimeFormatter formatter = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String sendAlarm(AlarmRequest request) {
        try {
            // Set timestamp if not provided
            if (request.getTimestamp() == null || request.getTimestamp().isEmpty()) {
                request.setTimestamp(LocalDateTime.now().format(formatter));
            }

            // Build alarm message
            String formattedMessage = buildAlarmMessage(request);
            
            // Publish to SNS Topic
            String topicResult = snsClient.publishMessage(
                formattedMessage, 
                request.getSubject()
            );

            // If phone number is provided, send SMS
            if (request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty()) {
                snsClient.publishSMS(
                    request.getPhoneNumber(), 
                    buildSMSMessage(request)
                );
            }

            return "Alarm sent successfully! " + topicResult;

        } catch (Exception e) {
            return "Error sending alarm: " + e.getMessage();
        }
    }

    private String buildAlarmMessage(AlarmRequest request) {
        return String.format(
            "ALARM NOTIFICATION\n" +
            "==================\n" +
            "Time: %s\n" +
            "Severity: %s\n" +
            "Subject: %s\n" +
            "Message: %s\n" +
            "==================",
            request.getTimestamp(),
            request.getSeverity(),
            request.getSubject(),
            request.getMessage()
        );
    }

    private String buildSMSMessage(AlarmRequest request) {
        return String.format(
            "[%s] %s - %s",
            request.getSeverity(),
            request.getSubject(),
            request.getMessage()
        );
    }
}