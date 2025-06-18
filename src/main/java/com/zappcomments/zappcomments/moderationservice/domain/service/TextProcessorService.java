package com.zappcomments.zappcomments.moderationservice.domain.service;

import com.zappcomments.zappcomments.moderationservice.api.model.PostData;
import com.zappcomments.zappcomments.moderationservice.api.model.PostInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TextProcessorService {

    public static final double VALUE_CONSTANT = 0.10;
    private final RabbitTemplate rabbitTemplate;


    public PostData processorPost(PostInput postInput, Map<String, Object> headers) {

        int wordCount = postInput.getBody().replace(" ", "").length();
        double calculatedValue = wordCount * VALUE_CONSTANT;

        return PostData.builder()
                .id(headers.get("id") != null ? UUID.fromString(headers.get("id").toString()) : null)
                .wordCount(wordCount)
                .calculatedValue(calculatedValue)
                .build();

    }

    public void processText(PostData postData) {

        rabbitTemplate.convertAndSend("post-service.post-processing-result.v1.e",
                "", postData);

        log.info("Processed post with ID: {}, Word Count: {}, Calculated Value: {}",
                postData.getId(), postData.getWordCount(), postData.getCalculatedValue());
    }

}
