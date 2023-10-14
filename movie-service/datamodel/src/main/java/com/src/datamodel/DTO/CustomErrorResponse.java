package com.src.datamodel.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CustomErrorResponse {
    private OffsetDateTime timestamp;
    private int status;
    private String error;
    private String path;

    public CustomErrorResponse(OffsetDateTime timestamp, int status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }
}
