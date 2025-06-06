package com.zappcomments.zappcomments.moderationservice.api.controller;

import com.zappcomments.zappcomments.moderationservice.api.model.ModerationInput;
import com.zappcomments.zappcomments.moderationservice.api.model.ModerationOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moderate")
@RequiredArgsConstructor
public class ModerationController {

    @PostMapping
    public ModerationOutput moderate(ModerationInput input) {

        return ModerationOutput.builder()
                .reason("Content is acceptable")
                .approved(true)
                .build();
    }
}
