package com.bitcup.boggle.api;

import com.bitcup.boggle.board.Board;
import com.bitcup.boggle.solver.Solver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BoggleController {

    private Solver solver;

    public BoggleController(Solver solver) {
        this.solver = solver;
    }

    @PostMapping(value = "api/v1/solve")
    public ResponseEntity<SolutionDTO> solve(@RequestBody BoardDTO boardDTO) {
        //todo: validation of the input boardDTO
        Board board = new Board(boardDTO.getLetters());
        long start = System.currentTimeMillis();
        List<String> validWords = solver.findValidWords(board, boardDTO.getMinWordLength());
        SolutionDTO solutionDTO = new SolutionDTO(validWords, System.currentTimeMillis() - start);
        return new ResponseEntity<>(solutionDTO, HttpStatus.OK);
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnNoFavicon() {
    }

}
