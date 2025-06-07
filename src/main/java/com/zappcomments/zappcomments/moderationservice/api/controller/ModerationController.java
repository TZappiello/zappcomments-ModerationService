package com.zappcomments.zappcomments.moderationservice.api.controller;

import com.zappcomments.zappcomments.moderationservice.api.model.ModerationInput;
import com.zappcomments.zappcomments.moderationservice.api.model.ModerationOutput;
import com.zappcomments.zappcomments.moderationservice.domain.service.BadWordValidatorException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zappcomments.zappcomments.moderationservice.domain.service.BadWordValidatorService.validate;

@RestController
@RequestMapping("/api/moderate")
@RequiredArgsConstructor
public class ModerationController {

    private static final Logger logger = LoggerFactory.getLogger(ModerationController.class);

    @PostMapping
    public ModerationOutput moderate(@RequestBody ModerationInput input) {
        try {

            validate(input.getText().toLowerCase());

            logger.info("Comment approved: {}", input.getText());
            return ModerationOutput.builder()
                    .reason("Content is acceptable")
                    .approved(true)
                    .build();

        } catch (BadWordValidatorException e) {

            logger.warn("Comment rejected: {}. Reason: {}", input.getText(), e.getMessage());
            return ModerationOutput.builder()
                    .reason(e.getMessage())
                    .approved(false)
                    .build();
        }
    }


}
