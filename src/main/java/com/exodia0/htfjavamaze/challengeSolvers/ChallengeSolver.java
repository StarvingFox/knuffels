package com.exodia0.htfjavamaze.challengeSolvers;


import org.springframework.stereotype.Component;

@Component
public interface ChallengeSolver {
    String solve(String parameter);
}
