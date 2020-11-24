package com.exodia0.htfjavamaze.challengeSolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("What is the Perfect Number in the nth position (1-based)?")
public class PerfectNumberSolver implements ChallengeSolver {
    @Override
    public String solve(String parameter) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(parameter);
        int numebr = 0;
        while (m.find()){
            numebr =  Integer.parseInt(m.group());
            return ""+perfectNumber(numebr);
        }
        return null;
    }



    public boolean perfectNumber(double number){
        int sum = 0;
        for(int i = 1; i<= number/2; i++){
            if(number%(double)i == 0){
                sum+=i;
            }
        }
        return (double) sum == number;
    }
}
