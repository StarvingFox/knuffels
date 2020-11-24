package com.exodia0.htfjavamaze.domain;

import lombok.Data;

import java.util.List;

@Data
public class Maze {
    private String mazeId;
    private List<Cell> cells;
    private boolean[][] visited;

    public void setIsVisited(int x, int y, boolean visited){
        this.visited[y][x] = visited;
    }

    public boolean isExplored(int x, int y){
        return this.visited[y][x];
    }

    public int getMaxCoord(){
        return cells.stream().map(Cell::getX).max(Integer::compareTo).get();
    }

    public Cell getCell(int x, int y){
        return cells.stream().filter(c -> c.getX() == x && c.getY() == y).findFirst().get();
    }

}
