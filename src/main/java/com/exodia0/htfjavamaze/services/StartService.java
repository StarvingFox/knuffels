package com.exodia0.htfjavamaze.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StartService {
    private final SolverService solverService;

    public StartService(SolverService solverService) {
        this.solverService = solverService;
    }

    // starts the async solving of mazes
    // async allows us to solve multiple mazes at one time
    @Scheduled(initialDelay = 1000)
    public void start(){
        for (int i = 0; i < 10; i++) {
            solverService.solve();
        }
    }
}
