package com.exodia0.htfjavamaze.services;

import com.exodia0.htfjavamaze.domain.Cell;
import com.exodia0.htfjavamaze.domain.Maze;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PathFinder {
    public List<Cell> findPath(Maze maze){
        LinkedList<Cell> nextToVisit = new LinkedList<>();
        Cell start = getEntry(maze);
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) {
            Cell cur = nextToVisit.remove();

            if (!maze.isValidLocation(cur.getX(), cur.getY())
                    || maze.isExplored(cur.getX(), cur.getY())
            ) {
                continue;
            }

            if (maze.isWall(cur.getX(), cur.getY())) {
                maze.setVisited(cur.getX(), cur.getY(), true);
                continue;
            }

            if (maze.isExit(cur.getX(), cur.getY())) {
                return backtrackPath(cur);
            }

            for (int[] direction : DIRECTIONS) {
                Coordinate coordinate
                        = new Coordinate(
                        cur.getX() + direction[0],
                        cur.getY() + direction[1],
                        cur
                );
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    public Cell getEntry(Maze maze){
        return maze.getCells().stream().filter(c -> c.getX() == 0 && c.getY() == 0).collect(Collectors.toList()).get(0);
    }
}
