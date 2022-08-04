package com.cts.microcredential.inventory.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.cts.microcredential.inventory.model.SKU;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SqsSender {

    private QueueMessagingTemplate qMessagingTemplate;

    public SqsSender(AmazonSQSAsync sqsAsync) {
        this.qMessagingTemplate = new QueueMessagingTemplate(sqsAsync);
    }

    public void send(SKU sku) {
        log.info("Sending SKU to queue.. {}", sku);
        qMessagingTemplate.convertAndSend("product-queue-details", sku);
    }
}
