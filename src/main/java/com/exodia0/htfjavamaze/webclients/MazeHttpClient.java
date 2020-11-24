package com.exodia0.htfjavamaze.webclients;

import com.exodia0.htfjavamaze.domain.MazeAnswer;
import com.exodia0.htfjavamaze.domain.Maze;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MazeHttpClient {
    private final WebClient webClient;
    private final static String TEAM_ID = "ca33a444-a2e7-4ebf-9df2-ff3b424e56b6";
    private final static String BASE_URI = "http://maze-staging.bewire.org";

    public MazeHttpClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Maze getMaze() {
        String uri = BASE_URI + "/maze?teamId=" + TEAM_ID;
        Maze maze = webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Maze.class)
                .block();
        return maze;
    }

    //todo deze nakijken
    public boolean postAnswer(String mazeId, MazeAnswer mazeAnswer) {

        log.info("Sending: "+ mazeAnswer.toString());
        String uri = BASE_URI + "/maze/" + mazeId;
        try {
            webClient
                    .post()
                    .uri(uri)
                    .body(Mono.just(mazeAnswer),MazeAnswer.class)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                        log.error("answer incorrect");
                        return Mono.error(new NotCorrectException(clientResponse.toString()));
                    })
                    .onStatus(HttpStatus::isError, clientResponse -> {
                        log.error("other error");
                        return Mono.error(new NotCorrectException("Some error"));
                    })
                    .toBodilessEntity().block();
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        log.info("[ SUCCES ]");
        return true;
    }
}
