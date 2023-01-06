package com.rsv.learn.imageuploaderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class ResponseError {
    private LocalDateTime timeStamp;
    private String message;
    private List<String> errorDetails;
}
