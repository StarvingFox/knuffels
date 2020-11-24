package com.exodia0.htfjavamaze.services;

import com.exodia0.htfjavamaze.challengeSolvers.ChallengeSolver;
import com.exodia0.htfjavamaze.domain.Cell;
import com.exodia0.htfjavamaze.domain.CellAnswer;
import com.exodia0.htfjavamaze.domain.Maze;
import com.exodia0.htfjavamaze.domain.MazeAnswer;
import com.exodia0.htfjavamaze.webclients.MazeHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SolverService {
    private final MazeHttpClient httpClient;
    private final PathFinder pathFinder;
    private final Map<String, ChallengeSolver> challengeSolvers;


    public SolverService(MazeHttpClient httpClient, PathFinder pathFinder, Map<String, ChallengeSolver> challengeSolvers) {
        this.httpClient = httpClient;
        this.pathFinder = pathFinder;
        this.challengeSolvers = challengeSolvers;
    }

    @Async
    public void solve() {
        while (true) {
            boolean skip = false;
            Maze maze = httpClient.getMaze();
            List<Cell> path = pathFinder.findPath(maze);
            MazeAnswer answer = new MazeAnswer();
            answer.setCells(new ArrayList<>());
            if (path.isEmpty()) {
                log.info("No path");
            }
            for (Cell cell : path) {
                if (cell.isDecisionPoint()) {
                    ChallengeSolver solver = challengeSolvers.get(cell.getChallenge());
                    if (solver == null) {
                        skip = true;
                        break;
                    }
                    String solution = solver.solve(cell.getChallengeParameters());
                    answer.getCells().add(new CellAnswer(solution, cell.getChallengeId(), cell.getX(), cell.getY()));
                } else {
                    answer.getCells().add(new CellAnswer(null, null, cell.getX(), cell.getY()));
                }
            }

            if (skip) {
                continue;
            }

            httpClient.postAnswer(maze.getMazeId(), answer);
        }
    }
}
