package com.bitcup.boggle.dictionary;

import com.bitcup.boggle.board.Board;

import java.util.List;

public interface Dictionary {
    void loadDictionary(String name);
    List<String> getCandidateWords(Board board, int minWordLength);
}
