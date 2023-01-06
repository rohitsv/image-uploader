package com.rsv.learn.imageuploaderservice;

import com.rsv.learn.imageuploaderservice.config.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileUploadProperties.class})
public class ImageUploaderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageUploaderServiceApplication.class, args);
    }

}
