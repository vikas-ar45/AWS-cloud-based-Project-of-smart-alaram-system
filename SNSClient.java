package com.smartalarmsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Component
public class SNSClient {

    @Autowired
    private SnsClient snsClient;

    @Value("${aws.sns.topic-arn}")
    private String topicArn;

    public String publishMessage(String message, String subject) {
        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .subject(subject)
                    .topicArn(topicArn)
                    .build();

            PublishResponse result = snsClient.publish(request);
            return "Message published. Status: " + result.messageId();

        } catch (SnsException e) {
            System.err.println("Error: " + e.awsErrorDetails().errorMessage());
            return "Error publishing message: " + e.getMessage();
        }
    }

    public String publishSMS(String phoneNumber, String message) {
        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .phoneNumber(phoneNumber)
                    .build();

            PublishResponse result = snsClient.publish(request);
            return "SMS sent. Message ID: " + result.messageId();

        } catch (SnsException e) {
            System.err.println("Error: " + e.awsErrorDetails().errorMessage());
            return "Error sending SMS: " + e.getMessage();
        }
    }
}