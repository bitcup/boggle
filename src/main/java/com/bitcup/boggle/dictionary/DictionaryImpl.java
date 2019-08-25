package com.bitcup.boggle.dictionary;

import com.bitcup.boggle.board.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DictionaryImpl implements Dictionary {

    private Set<String> words = new HashSet<>();

    @PostConstruct
    private void init() {
        long start = System.currentTimeMillis();
        loadDictionary("/dictionary-yawl.txt");
        log.info("dictionary-yawl.txt loaded in {} ms - {} total words",
                System.currentTimeMillis() - start, words.size());
    }

    @Override
    public void loadDictionary(String name) {
        InputStream stream = getClass().getResourceAsStream(name);
        Scanner scanner = new Scanner(stream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line != null) {
                words.add(line.toUpperCase());
            }
        }
    }

    @Override
    public List<String> getCandidateWords(Board board, int minWordLength) {
        Set<Character> boardLetters = board.getAllLetters();
        return words.stream()
                .filter(w -> w.length() >= minWordLength)
                // exclude words that have letters NOT in the board
                .filter(w -> {
                    for (char c : w.toCharArray()) {
                        if (!boardLetters.contains(c)) {
                            return false;
                        }
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
