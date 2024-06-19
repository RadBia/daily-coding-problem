package com.myapp;

import java.util.LinkedList;
import java.util.Queue;

public class DailyCoddingProblem1 {

/*
You are given an M by N matrix consisting of booleans that represents a board.
Each True boolean represents a wall. Each False boolean represents a tile you can walk on.
Given this matrix, a start coordinate, and an end coordinate, return the minimum number of steps required to reach the end coordinate from the start.
If there is no possible path, then return null. You can move up, left, down, and right. You cannot move through walls.
You cannot wrap around the edges of the board.

For example, given the following board:
[[f, f, f, f],
[t, t, f, t],
[f, f, f, f],
[f, f, f, f]]
and start = (3, 0) (bottom left) and end = (0, 0) (top left), the minimum number of steps required to reach the end is 7, since we would need to go through (1, 2) because there is a wall everywhere else on the second row.
*/

    public static Integer findMinSteps(boolean[][] board, Point start, Point end) {
        int rows = board.length;
        int cols = board[0].length;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};


        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        boolean[][] visited = new boolean[rows][cols];
        visited[start.x][start.y] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point current = queue.poll();

                if (current.x == end.x && current.y == end.y) {
                    return steps;
                }

                for (int[] direction : directions) {
                    int newX = current.x + direction[0];
                    int newY = current.y + direction[1];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols &&
                        !board[newX][newY] && !visited[newX][newY]) {
                        queue.add(new Point(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }
            steps++;

        }
        return null;
    }

    public static void main(String[] args) {

        boolean[][] board = {
                {false, false, false, false},
                {true, true, false, true},
                {false, false, false, false},
                {false, false, false, false}};

        Point start = new Point(3, 0);
        Point end = new Point(0, 0);

        Integer result = findMinSteps(board, start, end);
        if (result != null) {
            System.out.println("Minimum number of steps: " + result);
        } else {
            System.out.println("No possible path");
        }
    }


    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
