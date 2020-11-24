package com.exodia0.htfjavamaze.services;

import com.exodia0.htfjavamaze.domain.Cell;
import com.exodia0.htfjavamaze.domain.Maze;
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
            //TODO: solve challenges for best path
            List<Cell> path = pathFinder.findPath(maze);

            if (path.isEmpty()) {
                log.info("No path");
            }
            for (Cell cell : path) {
                log.info(String.format("[ CELL ] x: %d, y: %d", cell.getX(), cell.getY()));
            }
        }
    }
}
