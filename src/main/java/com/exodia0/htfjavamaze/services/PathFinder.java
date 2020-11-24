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
        int size = maze.getMaxCoord() + 1;

        log.info("maze of size: " + size + "x" + size);
        Cell start = getEntry(maze);
        boolean[][] visited = new boolean[size][size];

        maze.setVisited(visited);

        List<Cell> path = new ArrayList<>();
        if (explore(maze, start, path)) {
            return path;
        }
        return Collections.emptyList();
    }

    private boolean explore(Maze maze, Cell cell, List<Cell> path) {
        // if explored we stop recursion and continue with other path
        if (maze.isExplored(cell.getX(), cell.getY())) {
            return false;
        }
        // add cell to the path
        path.add(cell);
        // we set the cell as being visited
        maze.setIsVisited(cell.getX(), cell.getY(), true);
        // if we have reached the destination we return true, this way we return the full path
        if (cell.getX() == maze.getMaxCoord() && cell.getY() == maze.getMaxCoord()) {
            return true;
        }

        // we add the sides for the borders of the maze
        addSides(cell, maze);

        // we attempt to move to all open sides of the cell
        for (Side side : SIDES) {
            try{
                // we check if the side is a wall, if the side is contained in the sides of the cell it is a wall
                if (!cell.getSides().contains(side)) {
                    // we get the next cell
                    Cell nextCell = getNextCell(maze, cell, side);
                    if (
                            // we continue down the path with the next cell
                        explore(maze, nextCell, path)
                ) {
                        // if we find the end in recursion this will return true, eventually returning the path
                    return true;
                }
            }
            }catch (Exception e){
                // catch if we try to fetch a non existant cell, should never happen
                log.info("fout bij ophalen van cell, zou niet mogen");
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    public Cell getEntry(Maze maze) {
        return maze.getCells().stream().filter(c -> c.getX() == 0 && c.getY() == 0).findFirst().get();
    }

    public void addSides(Cell cell, Maze maze){
        if(cell.getX() == maze.getMaxCoord()){
            cell.getSides().add(Side.EAST);
        }

        if(cell.getX() == 0){
            cell.getSides().add(Side.WEST);
        }

        if(cell.getY() == maze.getMaxCoord()){
            cell.getSides().add(Side.SOUTH);
        }

        if(cell.getY() == 0){
            cell.getSides().add(Side.NORTH);
        }
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

        return maze.getCell(x, y);
    }
}
