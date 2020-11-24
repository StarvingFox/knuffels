package com.exodia0.htfjavamaze.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StartService {
    private final SolverService solverService;
    private final BruteForceSimpleMazeSolver bruteForceSimpleMazeSolver;

    public StartService(SolverService solverService, BruteForceSimpleMazeSolver bruteForceSimpleMazeSolver) {
        this.solverService = solverService;
        this.bruteForceSimpleMazeSolver = bruteForceSimpleMazeSolver;
    }

    // starts the async solving of mazes
    // async allows us to solve multiple mazes at one time
    @Scheduled(initialDelay = 1000, fixedRate = 10000000L)
    public void start(){
        for (int i = 0; i < 10; i++) {
            bruteForceSimpleMazeSolver.solve();
        }

        for (int i = 0; i < 10; i++) {
            solverService.solve();
        }
    }
}
