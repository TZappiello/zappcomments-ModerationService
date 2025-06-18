package com.zappcomments.zappcomments.moderationservice.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostInputConsumer {

    private String postId;
    private String postBody;

}
