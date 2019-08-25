package com.bitcup.boggle.solver;

import com.bitcup.boggle.board.Board;
import com.bitcup.boggle.dictionary.Dictionary;
import com.bitcup.boggle.trie.Trie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Based on http://www.geekviewpoint.com/java/boggle/solver
 */
@Slf4j
@Component
public class SolverImpl implements Solver {

    private final Dictionary dictionary;
    private Trie trie;

    public SolverImpl(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @PostConstruct
    private void init() {
        long start = System.currentTimeMillis();
        trie = new Trie();
        Set<String> dictionaryWords = dictionary.getAllWords();
        for (String word : dictionaryWords) {
            trie.addWord(word);
        }
        log.info("trie built from {} dictionary words in {} ms",
                dictionaryWords.size(), System.currentTimeMillis() - start);
    }

    @Override
    public List<String> findValidWords(Board board, int minWordLength) {
        StopWatch sw = new StopWatch();

        sw.start("search for words");
        Set<String> validWords = new HashSet<>();
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                boolean[][] used = new boolean[board.getRows()][board.getColumns()];
                searchForWords(board, used, trie, minWordLength, Character.toString(board.getLetter(row, col)), row, col, validWords);
            }
        }
        sw.stop();

        sw.start("sort words");
        List<String> sorted = new ArrayList<>(validWords);
        Collections.sort(sorted);
        sw.stop();

        log.info("found {} valid words - timing: \n{}", sorted.size(), sw.prettyPrint());
        return sorted;
    }

    private void searchForWords(Board board, boolean[][] used, Trie trie, int minWordLength,
                                String partialWord, int row, int col, Set<String> validWords) {

        if (trie.isWord(partialWord) && partialWord.length() >= minWordLength) {
            validWords.add(partialWord);
        }

        if (!trie.isPrefix(partialWord)) {
            return;
        }

        boolean[][] updatedUsed = copy(used);
        updatedUsed[row][col] = true;

        // upper left cell
        if (0 <= row - 1 && 0 <= col - 1 && !updatedUsed[row - 1][col - 1]) {
            searchForWords(board, updatedUsed, trie, minWordLength, partialWord + board.getLetter(row - 1, col - 1), row - 1, col - 1, validWords);
        }

        // up cell
        if (0 <= col - 1 && !updatedUsed[row][col - 1]) {
            searchForWords(board, updatedUsed, trie, minWordLength, partialWord + board.getLetter(row, col - 1), row, col - 1, validWords);
        }

        // upper right cell
        if (row + 1 < board.getRows() && 0 <= col - 1 && !updatedUsed[row + 1][col - 1]) {
            searchForWords(board, updatedUsed, trie, minWordLength, partialWord + board.getLetter(row + 1, col - 1), row + 1, col - 1, validWords);
        }

        // right cell
        if (row + 1 < board.getRows() && !updatedUsed[row + 1][col]) {
            searchForWords(board, updatedUsed, trie, minWordLength, partialWord + board.getLetter(row + 1, col), row + 1, col, validWords);
        }

        // lower right cell
        if (row + 1 < board.getRows() && col + 1 < board.getColumns() && !updatedUsed[row + 1][col + 1]) {
            searchForWords(board, updatedUsed, trie, minWordLength, partialWord + board.getLetter(row + 1, col + 1), row + 1, col + 1, validWords);
        }

        // down cell
        if (col + 1 < board.getColumns() && !updatedUsed[row][col + 1]) {
            searchForWords(board, updatedUsed, trie, minWordLength, partialWord + board.getLetter(row, col + 1), row, col + 1, validWords);
        }

        // lower left cell
        if (0 <= row - 1 && col + 1 < board.getColumns() && !updatedUsed[row - 1][col + 1]) {
            searchForWords(board, updatedUsed, trie, minWordLength, partialWord + board.getLetter(row - 1, col + 1), row - 1, col + 1, validWords);
        }

        // left cell
        if (0 <= row - 1 && !updatedUsed[row - 1][col]) {
            searchForWords(board, updatedUsed, trie, minWordLength, partialWord + board.getLetter(row - 1, col), row - 1, col, validWords);
        }
    }

    private boolean[][] copy(boolean[][] used) {
        boolean[][] copy = new boolean[used.length][used[0].length];
        for (int row = 0; row < used.length; row++) {
            for (int col = 0; col < used[0].length; col++) {
                if (used[row][col]) {
                    copy[row][col] = used[row][col];
                }
            }
        }
        return copy;
    }
}
