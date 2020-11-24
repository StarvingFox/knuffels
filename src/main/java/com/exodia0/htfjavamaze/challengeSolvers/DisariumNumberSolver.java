package com.exodia0.htfjavamaze.challengeSolvers;

import org.springframework.stereotype.Component;

@Component
public class DisariumNumberSolver implements ChallengeSolver {
    @Override
    public String solve(String parameter) {
        var length = parameter.length();
        var numbers = parameter.split("");
        double sum = 0;
        double inputNumber = Double.parseDouble(parameter);
        for(int i = 0; i<length; i++){
            double number = Double.parseDouble(numbers[i]);

        }
        return null;
    }

    @Override
    public String getChallengeString() {
        return "Is the following number a Disarium Number? (Use N or Y as answer)";
    }
}
