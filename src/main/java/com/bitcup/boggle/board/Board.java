package com.bitcup.boggle.board;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Board {

    private final char[][] letters;

    public Board(char[][] letters) {
        this.letters = letters;
    }

    public Board(String letters) {
        StringTokenizer st = new StringTokenizer(letters, "\n");
        int numRows = st.countTokens();
        int numCols = letters.indexOf("\n");
        this.letters = new char[numRows][numCols];
        int row = 0;
        while (st.hasMoreTokens()) {
            String line = st.nextToken();
            for (int col=0; col<line.length(); col++) {
                this.letters[row][col] = line.charAt(col);
            }
            row++;
        }
    }

    public int getRows() {
        return letters.length;
    }

    public int getColumns() {
        return letters[0].length;
    }

    public char getLetter(int i, int j) {
        return letters[i][j];
    }

    public Set<Character> getAllLetters() {
        Set<Character> all = new HashSet<>();
        for (char[] row : letters) {
            for (char c : row) {
                all.add(c);
            }
        }
        return all;
    }

}
