package com.exodia0.htfjavamaze.domain;

import lombok.Data;

@Data
public class CellAnswer {
    private String answer;
    private String challengeId;
    private int x;
    private int y;


    public CellAnswer(String answer, String challengeId, int x, int y) {
        this.answer = answer;
        this.challengeId = challengeId;
        this.x = x;
        this.y = y;
    }
}
