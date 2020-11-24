package com.exodia0.htfjavamaze.challengeSolvers;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class DecodeStringSolver implements ChallengeSolver{
    @Override
    public String solve(String parameter) {
        byte[] decodedBytes = Base64.getDecoder().decode(parameter);
        return new String(decodedBytes);
    }

    @Override
    public String getChallengeString() {
        return "Decode the following String. It uses a quite common encoding, find out which!";
    }
}
