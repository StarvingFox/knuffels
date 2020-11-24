package com.exodia0.htfjavamaze.webclients;

import com.exodia0.htfjavamaze.domain.Maze;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class MazeHttpClient {
    private final WebClient webClient;
    private final static String TEAM_ID = "ca33a444-a2e7-4ebf-9df2-ff3b424e56b6";
    private final static String BASE_URI = "http://maze-staging.bewire.org";

    public MazeHttpClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Maze getMaze(){
        String uri = BASE_URI+"/maze?teamId="+TEAM_ID;
       Maze maze = webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Maze.class)
                .block();

       log.info(maze.toString());

       return maze;
    }
}
