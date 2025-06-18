package com.zappcomments.zappcomments.moderationservice.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostInput {

    private String title;
    private String body;
    private String author;
}
