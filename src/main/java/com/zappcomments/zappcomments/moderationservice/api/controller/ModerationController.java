package com.zappcomments.zappcomments.moderationservice.api.controller;

import com.zappcomments.zappcomments.moderationservice.api.model.ModerationInput;
import com.zappcomments.zappcomments.moderationservice.api.model.ModerationOutput;
import com.zappcomments.zappcomments.moderationservice.domain.service.BadWordValidatorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zappcomments.zappcomments.moderationservice.domain.service.BadWordValidatorService.validate;

@RestController
@RequestMapping("/api/moderate")
@RequiredArgsConstructor
@Slf4j
public class ModerationController {

    @PostMapping
    public ModerationOutput moderate(@RequestBody ModerationInput input) {
        try {

            validate(input.getText().toLowerCase());

            log.info("Comment approved: {}", input.getText());
            return ModerationOutput.builder()
                    .reason("Content is acceptable")
                    .approved(true)
                    .build();

        } catch (BadWordValidatorException e) {

            log.warn("Comment rejected: {}. Reason: {}", input.getText(), e.getMessage());
            return ModerationOutput.builder()
                    .reason(e.getMessage())
                    .approved(false)
                    .build();
        }
    }


}
