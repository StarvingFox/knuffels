package com.exodia0.htfjavamaze.domain;

import lombok.Data;

import java.util.List;

@Data
public class MazeAnswer {
    private List<CellAnswer> cells;
}
