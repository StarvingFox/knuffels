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
                getCellsBruteForce(maze);
            }
        }
    }

    public void getCellsBruteForce(Maze maze){
        List<CellAnswer> answers = new ArrayList<>();
        answers.add(new CellAnswer(null, null, 0, 0));
        answers.add(new CellAnswer(null, null, 1, 0));
        answers.add(new CellAnswer(null, null, 1, 1));

        sendAnswer(maze.getMazeId(), answers);

        List<CellAnswer> answers2 = new ArrayList<>();
        answers2.add(new CellAnswer(null, null, 0, 0));
        answers2.add(new CellAnswer(null, null, 0, 1));
        answers2.add(new CellAnswer(null, null, 1, 1));
        sendAnswer(maze.getMazeId(), answers2);
    }

    public void sendAnswer(String mazeId, List<CellAnswer> cells){
        MazeAnswer mazeAnswer = new MazeAnswer();
        mazeAnswer.setCells(cells);
        httpClient.postAnswer(mazeId, mazeAnswer);
//        if(httpClient.postAnswer(mazeId, mazeAnswer)){
//            log.info("[SUCCESS] "+ mazeId);
//        }else{
//            log.info("[FAIl] "+ mazeId);
//        }
    }


}
