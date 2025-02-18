package com.lab;

import java.util.Scanner;
import java.io.InputStream;

public class Minesweeper {
    static char SAFE_CELL = '.';
    static char MINE_CELL = 'X';
    static int IS_SAFE = 0;
    static int IS_MINE = 1;
    int fieldX, fieldY;
    int[][] cells;
    String fieldFileName;

    public Minesweeper(String fieldFile) {
        this.fieldFileName = fieldFile;
        initFromFile(fieldFileName);
    }

    public Minesweeper(int fieldX, int fieldY) {
        this.fieldX = fieldX;
        this.fieldY = fieldY;
        this.cells = new int[fieldX][fieldY];
        for (int i = 0; i < fieldX; i++) {
            for (int j = 0; j < fieldY; j++) {
                cells[i][j] = IS_SAFE;
            }
        }
    }

    void displayField() {
        // Task 1: Display the mine field to terminal
        for (int i = 0; i < fieldX; i++) {
            for (int j = 0; j < fieldY; j++) {
                System.out.print(cells[i][j] == IS_MINE ? MINE_CELL : SAFE_CELL);
            }
            System.out.println();
        }
    }

    void setMineCell(int x, int y) {
        cells[x][y] = IS_MINE;
    }

    void initFromFile(String mineFieldFile) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(mineFieldFile);
        // Task 2: Using java.util.Scanner to load mine field from the input stream
        // named, isSSs
        if (is == null) {
            System.err.println("File not found: " + mineFieldFile);
            return;
        }
        try (Scanner scanner = new Scanner(is)) {
            fieldX = scanner.nextInt();
            fieldY = scanner.nextInt();
            cells = new int[fieldX][fieldY];
            scanner.nextLine();
            for (int i = 0; i < fieldX; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < fieldY; j++) {
                    cells[i][j] = (line.charAt(j) == MINE_CELL) ? IS_MINE : IS_SAFE;
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
