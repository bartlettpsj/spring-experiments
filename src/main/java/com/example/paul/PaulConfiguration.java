package com.example.paul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail")
@Getter @Setter
public class PaulConfiguration {
    private String hostName;
    private int port;
    private String from;
    private String nickName;
}
