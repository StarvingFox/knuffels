package com.exodia0.htfjavamaze.domain;

import lombok.Data;

import java.util.List;

@Data
public class Maze {
    private String mazeId;
    private List<Cell> cells;
}
