package com.rsv.learn.imageuploaderservice.exception;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class FileNotFoundException extends RuntimeException {
    @NonNull
    private String message;
    private List<String> errorDetails;
}
