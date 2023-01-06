package com.rsv.learn.imageuploaderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Builder
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class FileUploadResponse {
    private String fileName;
    private String message;
}
