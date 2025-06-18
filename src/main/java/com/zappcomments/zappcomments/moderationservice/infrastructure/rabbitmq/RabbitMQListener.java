package com.zappcomments.zappcomments.moderationservice.infrastructure.rabbitmq;

import com.zappcomments.zappcomments.moderationservice.api.model.PostInput;
import com.zappcomments.zappcomments.moderationservice.api.model.PostData;
import com.zappcomments.zappcomments.moderationservice.domain.service.TextProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.zappcomments.zappcomments.moderationservice.infrastructure.rabbitmq.RabbitMQConfig.TEXT_PROCESSOR_SERVICE_POST_PROCESSING;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final TextProcessorService processText;

    @RabbitListener(queues = TEXT_PROCESSOR_SERVICE_POST_PROCESSING)
    public void handlerTextProcessor(@Payload PostInput postInput,
                                     @Headers Map<String, Object> headers) {
        PostInput build = PostInput.builder()
                .title(postInput.getTitle())
                .body(postInput.getBody())
                .author(postInput.getAuthor())
                .build();

        PostData postData = processText.processorPost(build, headers);

        processText.processText(postData);

        log.info("PostInput received: {}", build);
        log.info("Headers received: {}", headers);
    }


}
