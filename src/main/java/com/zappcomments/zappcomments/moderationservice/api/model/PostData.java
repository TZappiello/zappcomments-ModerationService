package com.zappcomments.zappcomments.moderationservice.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostData {

    private String id;
    private Integer wordCount;
    private Double calculatedValue;
}
