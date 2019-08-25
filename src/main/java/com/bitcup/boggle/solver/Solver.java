package com.bitcup.boggle.solver;

import com.bitcup.boggle.board.Board;

import java.util.List;

public interface Solver {
    List<String> findValidWords(Board board, int minWordLength);
}
