package com.zappcomments.zappcomments.moderationservice.domain.service;

import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class BadWordValidatorService {

    private BadWordValidatorService() {}

    private static final Set<String> BANNED_WORDS = Set.of(
            "ódio", "xingamento", "palavrão"
    );

    public static void validate(String text) {
        for (String prohibited : BANNED_WORDS) {
            if (text.contains(prohibited)) {
                throw new BadWordValidatorException("Comment contains inappropriate language: " + prohibited);
            }
        }
    }
}