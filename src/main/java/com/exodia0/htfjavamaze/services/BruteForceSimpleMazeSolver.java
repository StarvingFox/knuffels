package com.exodia0.htfjavamaze.services;

import com.exodia0.htfjavamaze.domain.*;
import com.exodia0.htfjavamaze.webclients.MazeHttpClient;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
//                log.info(maze.toString());
                sendAnswer(maze.getMazeId(), getCellsBruteForce(maze));
            }
        }
    }

    public List<CellAnswer> getCellsBruteForce(Maze maze){
        List<CellAnswer> answers = new ArrayList<>();
        var firstCell = maze.getCells().stream().filter(c -> c.getX() == 0 && c.getY() == 0).findFirst().get();
        answers.add(new CellAnswer("", "", firstCell.getX(), firstCell.getY()));
        if(firstCell.getSides().contains(Side.EAST)){
            answers.add(new CellAnswer("", "", 0, 1));
            answers.add(new CellAnswer("", "", 1, 1));
        }
        else{
            answers.add(new CellAnswer("", "", 1, 0));
            answers.add(new CellAnswer("", "", 1, 1));
        }

        return answers;
    }

    public void sendAnswer(String mazeId, List<CellAnswer> cells){
        MazeAnswer mazeAnswer = new MazeAnswer();
        mazeAnswer.setCells(cells);
        if(httpClient.postAnswer(mazeId, mazeAnswer)){
            log.info("[SUCCESS] "+ mazeId);
        }else{
            log.info("[FAIl] "+ mazeId);
        }
    }


}
