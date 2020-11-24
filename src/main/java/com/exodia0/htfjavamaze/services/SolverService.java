package com.exodia0.htfjavamaze.services;

import com.exodia0.htfjavamaze.domain.Cell;
import com.exodia0.htfjavamaze.domain.CellAnswer;
import com.exodia0.htfjavamaze.domain.Maze;
import com.exodia0.htfjavamaze.domain.MazeAnswer;
import com.exodia0.htfjavamaze.webclients.MazeHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SolverService {
    private final MazeHttpClient httpClient;
    private final PathFinder pathFinder;


    public SolverService(MazeHttpClient httpClient, PathFinder pathFinder) {
        this.httpClient = httpClient;
        this.pathFinder = pathFinder;
    }

    @Async
    public void solve(){
        while(true) {
            Maze maze = httpClient.getMaze();
            List<Cell> path = pathFinder.findPath(maze);
            MazeAnswer answer = new MazeAnswer();
            if (path.isEmpty()) {
                log.info("No path");
            }
            for (Cell cell : path) {
                if(cell.isDecisionPoint()){

                }else{
                    answer.getCells().add(new CellAnswer(null, null, cell.getX(), cell.getY()));
                }
            }

            httpClient.postAnswer(maze.getMazeId(), answer);
        }
    }
}
