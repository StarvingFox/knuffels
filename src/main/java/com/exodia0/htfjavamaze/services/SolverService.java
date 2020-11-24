package com.exodia0.htfjavamaze.services;

import com.exodia0.htfjavamaze.webclients.MazeHttpClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SolverService {
    private final MazeHttpClient httpClient;


    public SolverService(MazeHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Async
    public void solve(){
        httpClient.getMaze();

        //todo: find best path
        //TODO: solve challenges for best path
    }
}
