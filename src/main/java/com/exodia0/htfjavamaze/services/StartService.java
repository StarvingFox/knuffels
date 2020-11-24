package com.exodia0.htfjavamaze.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StartService {
    private final SolverService solverService;

    public StartService(SolverService solverService) {
        this.solverService = solverService;
    }

    @Scheduled(initialDelay = 1000)
    public void start(){
        solverService.solve();
    }
}
