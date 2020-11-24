package com.exodia0.htfjavamaze.challengeSolvers;

import org.springframework.stereotype.Component;


public interface ChallengeSolver {

    String solve(String parameter);
    String getChallengeString();
}
