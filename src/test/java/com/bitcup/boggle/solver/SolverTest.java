package com.bitcup.boggle.solver;

import com.bitcup.boggle.board.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolverTest {

    @Autowired
    private Solver solver;

    @Test
    public void testBoard3x3() {
        char[][] board = {
                {'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}
        };
        Board boggleBoard = new Board(board);
        List<String> validWords = solver.findValidWords(boggleBoard, 5);
        assertThat(validWords.size()).isEqualTo(3);
        assertThat(validWords).containsExactly("GEEKS", "SQUEG", "SQUIZ");
    }

    @Test
    public void testBoard5x5() {
        char[][] board = {
                {'O', 'C', 'Y', 'A', 'L'},
                {'N', 'A', 'N', 'P', 'N'},
                {'S', 'R', 'I', 'U', 'Z'},
                {'E', 'M', 'O', 'C', 'Y'},
                {'T', 'R', 'U', 'E', 'P'}
        };
        Board boggleBoard = new Board(board);
        List<String> validWords = solver.findValidWords(boggleBoard, 8);
        assertThat(validWords.size()).isEqualTo(6);
        assertThat(validWords).containsExactly("COURTERS", "COURTESAN", "MORAINAL", "PANICUMS", "TERMINAL", "UNICORNS");
    }
}
