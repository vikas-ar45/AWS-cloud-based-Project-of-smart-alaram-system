package com.smartalarmsystem.model;

public class AlarmRequest {
    private String message;
    private String subject;
    private String severity;
    private String timestamp;
    private String phoneNumber;
    private String email;

    // Default constructor
    public AlarmRequest() {
    }

    // All-args constructor
    public AlarmRequest(String message, String subject, String severity, String timestamp, String phoneNumber, String email) {
        this.message = message;
        this.subject = subject;
        this.severity = severity;
        this.timestamp = timestamp;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString method
    @Override
    public String toString() {
        return "AlarmRequest{" +
                "message='" + message + '\'' +
                ", subject='" + subject + '\'' +
                ", severity='" + severity + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}