package com.exodia0.htfjavamaze.domain;



import lombok.Data;

import java.util.List;

@Data
public class Cell {
    private String challenge;
    private String challengeId;
    private String challengeParameters;
    private boolean decisionPoint;
    private int prize;
    private List<Side> sides;
    private int x;
    private int y;


}
