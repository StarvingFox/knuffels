package com.exodia0.htfjavamaze.services;

import com.exodia0.htfjavamaze.webclients.MazeHttpClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BruteForceSimpleMazeSolver {
    private final MazeHttpClient httpClient;

    public BruteForceSimpleMazeSolver(MazeHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Async
    public void solve(){
        while(true){
            //todo if grote dan 2x2 skip
            //todo alle combos sturen
        }
    }
}
