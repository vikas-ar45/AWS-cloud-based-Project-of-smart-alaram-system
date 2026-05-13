package com.smartalarmsystem;

import com.smartalarmsystem.model.AlarmRequest;
import com.smartalarmsystem.service.AlarmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AlarmServiceTest {

    @Autowired
    private AlarmService alarmService;

    @BeforeEach
    public void setup() {
        assertNotNull(alarmService);
    }

    @Test
    public void testSendAlarm() {
        AlarmRequest request = new AlarmRequest();
        request.setMessage("Test alarm message");
        request.setSubject("Test Subject");
        request.setSeverity("HIGH");

        String result = alarmService.sendAlarm(request);
        assertNotNull(result);
    }
}