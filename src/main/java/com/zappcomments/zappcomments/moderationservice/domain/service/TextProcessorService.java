package com.zappcomments.zappcomments.moderationservice.domain.service;

import com.zappcomments.zappcomments.moderationservice.api.model.PostData;
import com.zappcomments.zappcomments.moderationservice.api.model.PostInputConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
@Slf4j
public class TextProcessorService {

    public static final String POST_SERVICE_POST_PROCESSING_RESULT = "post-service.post-processing-result.v1.e";
    public static final double VALUE_CONSTANT = 0.10;
    private final RabbitTemplate rabbitTemplate;


    public PostData processorPost(PostInputConsumer postInputConsumer) {

        int wordCount = postInputConsumer.getPostBody().replace(" ", "").length();

        BigDecimal calculatedValue = BigDecimal.valueOf(wordCount)
                .multiply(BigDecimal.valueOf(VALUE_CONSTANT))
                .setScale(2, RoundingMode.HALF_UP);

        return PostData.builder()
                .id(postInputConsumer.getPostId())
                .wordCount(wordCount)
                .calculatedValue(calculatedValue.doubleValue())
                .build();

    }

    public void processText(PostData postData) {

        rabbitTemplate.convertAndSend(POST_SERVICE_POST_PROCESSING_RESULT,
                "", postData);

        log.info("Processed post with ID: {}, Word Count: {}, Calculated Value: {}",
                postData.getId(), postData.getWordCount(), postData.getCalculatedValue());
    }

}
