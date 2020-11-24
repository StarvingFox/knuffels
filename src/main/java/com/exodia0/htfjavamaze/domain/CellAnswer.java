package com.exodia0.htfjavamaze.domain;

import lombok.Data;

@Data
public class CellAnswer {
    private String answer;
    private String challengeId;
    private int x;
    private int y;
}
