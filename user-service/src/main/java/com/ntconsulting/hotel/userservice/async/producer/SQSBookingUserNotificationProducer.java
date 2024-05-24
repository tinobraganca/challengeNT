package com.ntconsulting.hotel.userservice.async.producer;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SQSBookingUserNotificationProducer {

    private final SqsTemplate sqsTemplate;

    @Value("${spring.application.sqs.booking.warning.hotel}")
    private String queue;

    @Autowired
    public SQSBookingUserNotificationProducer(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }

    public void sendStatus(String message){
        sqsTemplate.send(sqsSendOptions ->
                sqsSendOptions
                        .queue(queue)
                        .payload(message));
    }
}
