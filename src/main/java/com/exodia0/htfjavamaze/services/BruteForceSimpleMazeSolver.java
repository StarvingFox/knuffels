package com.exodia0.htfjavamaze.services;

import com.exodia0.htfjavamaze.domain.*;
import com.exodia0.htfjavamaze.webclients.MazeHttpClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BruteForceSimpleMazeSolver {
    private final MazeHttpClient httpClient;

    public BruteForceSimpleMazeSolver(MazeHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Async
    public void solve(){
        while(true){
            //todo if grote dan 2x2 skip
            var maze = httpClient.getMaze();
            if(maze.getCells().size() <= 4){
                sendAnswer(maze.getMazeId(), getCellsBruteForce(maze));
            }

        }
    }

    public List<Cell> getCellsBruteForce(Maze maze){
        var firstCell = maze.getCells().stream().filter(c -> c.getX() == 0 && c.getY() == 0).findFirst().get();
        if(firstCell.getSides().contains(Side.EAST)){
            return maze.getCells().stream().filter(c -> c.getX() != 1 && c.getY() != 0).collect(Collectors.toList());
        }
        else{
            return maze.getCells().stream().filter(c -> c.getX() != 0 && c.getY() != 1).collect(Collectors.toList());
        }

    }

    public void sendAnswer(String mazeId, List<Cell> cells){
        var mazeAnswer = new MazeAnswer();
        mazeAnswer.setCells(new ArrayList<>());
        for (Cell cell : cells) {
            mazeAnswer.getCells().add(new CellAnswer("", cell.getChallengeId(), cell.getX(), cell.getY()));
        }
        httpClient.postAnswer(mazeId, mazeAnswer);
    }


}
