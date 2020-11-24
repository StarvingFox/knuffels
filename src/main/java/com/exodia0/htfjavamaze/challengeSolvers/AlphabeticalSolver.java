package com.exodia0.htfjavamaze.challengeSolvers;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("Is the following string in alphabetical order? (Use N or Y as answer)")
public class AlphabeticalSolver implements ChallengeSolver{
    @Override
    public String solve(String parameter) {
        char[] arr = parameter.toCharArray();
        Arrays.sort(arr);
        String correct = arr.toString();

        return correct.equals(parameter)?"Y":"N";
    }
}
