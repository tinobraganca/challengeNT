package com.ntconsulting.hotel.userservice.async.producer;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SQSPaymentUserNotificationProducer {

    private final SqsTemplate sqsTemplate;

    @Value("${spring.application.sqs.payment.warning.user}")
    private String queue;

    @Autowired
    public SQSPaymentUserNotificationProducer(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }

    public void sendPaymentStatus(String message){
        sqsTemplate.send(sqsSendOptions ->
                sqsSendOptions
                        .queue(queue)
                        .payload(message));
    }
}
