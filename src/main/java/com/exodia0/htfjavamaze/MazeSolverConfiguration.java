package com.exodia0.htfjavamaze;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Configuration
public class MazeSolverConfiguration {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("maze-staging.bewire.org")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)).build();
    }
}
