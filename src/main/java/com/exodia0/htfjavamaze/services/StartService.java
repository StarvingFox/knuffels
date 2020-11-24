package com.exodia0.htfjavamaze.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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
    @PostConstruct
    public void start() {
        bruteForceSimpleMazeSolver.solve();
        solverService.solve();
    }
}
