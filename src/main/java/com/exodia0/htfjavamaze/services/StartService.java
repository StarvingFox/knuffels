package com.exodia0.htfjavamaze.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StartService {
    private final SolverService solverService;

    public StartService(SolverService solverService) {
        this.solverService = solverService;
    }

    // starts the async solving of mazes
    // async allows us to solve multiple mazes at one time
    @PostConstruct
    public void start(){
        for (int i = 0; i < 1; i++) {
            solverService.solve();
        }
    }
}
