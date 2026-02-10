package com.examify.exam.kafka;

import com.examify.exam.event.ExamSubmittedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExamEventProducer {

    private final KafkaTemplate<String, ExamSubmittedEvent> kafkaTemplate;

    public ExamEventProducer(KafkaTemplate<String, ExamSubmittedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendExamSubmittedEvent(ExamSubmittedEvent event) {
        kafkaTemplate.send("exam-submitted", event.getUserEmail(), event);
    }
}
