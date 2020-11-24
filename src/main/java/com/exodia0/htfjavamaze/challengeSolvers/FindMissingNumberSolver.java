package com.exodia0.htfjavamaze.challengeSolvers;

public class FindMissingNumberSolver implements ChallengeSolver {
    @Override
    public String solve(String parameter) {
        var arrayAsString = parameter.replace("[", "").replace("]", "");
        var numbers = arrayAsString.split("");
        for (String number : numbers) {
            try{

            }
            catch (Exception e){

            }
        }
        return "";
    }

    @Override
    public String getChallengeString() {
        return "Find the missing numbers in the sequence. Return them as a comma-separated string";
    }
}
