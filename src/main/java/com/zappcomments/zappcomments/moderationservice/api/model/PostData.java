package com.zappcomments.zappcomments.moderationservice.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostData {

    private UUID id;
    private Integer wordCount;
    private Double calculatedValue;
}
