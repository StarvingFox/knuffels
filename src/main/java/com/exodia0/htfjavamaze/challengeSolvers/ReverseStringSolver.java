package com.exodia0.htfjavamaze.challengeSolvers;

import org.springframework.stereotype.Component;

@Component
public class ReverseStringSolver implements ChallengeSolver {
    @Override
    public String solve(String parameter) {
        int length = parameter.length();
        char[] inputAsArray = parameter.toCharArray();
        char[] reversed = new char[length];
        for(int i = 0; i<length; i++){
            reversed[i] = inputAsArray[length-i-1];
        }
        return new String(reversed);

    }

    @Override
    public String getChallengeString() {
        return "Reverse the following String";
    }
}
