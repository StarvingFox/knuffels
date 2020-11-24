package com.exodia0.htfjavamaze.services;

import com.exodia0.htfjavamaze.domain.Cell;
import com.exodia0.htfjavamaze.domain.Maze;
import com.exodia0.htfjavamaze.domain.Side;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PathFinder {
    private final Side[] SIDES = Side.values();

    public List<Cell> findPath(Maze maze) {
        log.info("SOLVING maze: " + maze.getMazeId());
        int size = maze.getCells().stream().map(Cell::getX).max(Integer::compareTo).get();

        log.info("maze of size: " + size + "x" + size);
        Cell start = getEntry(maze);
//        boolean[][] visited = new boolean[size][size];

        List<Cell> path = new ArrayList<>();
        if (explore(maze, start, path)) {
            return path;
        }
        return Collections.emptyList();
    }

    private boolean explore(Maze maze, Cell cell, List<Cell> path) {
        if (
                !maze.isValidLocation(row, col)
                        || maze.isWall(row, col)
                        || maze.isExplored(row, col)
        ) {
            return false;
        }

        path.add(cell);
        maze.setVisited(row, col, true);

        if (maze.isExit(row, col)) {
            return true;
        }

        for (Side side : SIDES) {
            if ()
                Coordinate coordinate = getNextCoordinate(
                        row, col, direction[0], direction[1]);
            if (
                    explore(
                            maze,
                            coordinate.getX(),
                            coordinate.getY(),
                            path
                    )
            ) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    public Cell getEntry(Maze maze) {
        return maze.getCells().stream().filter(c -> c.getX() == 0 && c.getY() == 0).findFirst().get();
    }

    public boolean isValidLocation(int x, int y) {

    }

    public Cell getNextCell(Maze maze, Cell cell, Side side) {
        int x = cell.getX();
        int y = cell.getY();
        switch (side) {
            case SOUTH:
                y++;
                break;
            case NORTH:
                y--;
                break;
            case EAST:
                x++;
                break;
            case WEST:
                x--;
                break;
        }

        return maze.getCells().stream().filter(c -> c.getX() == x && c.getY() == y).findFirst().get();
    }
}
