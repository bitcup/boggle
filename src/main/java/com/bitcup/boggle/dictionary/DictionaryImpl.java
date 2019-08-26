package com.bitcup.boggle.dictionary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Slf4j
@Component
public class DictionaryImpl implements Dictionary {

    private Set<String> words = new HashSet<>();

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
    public Set<String> getAllWords() {
        if (words.isEmpty()) {
            long start = System.currentTimeMillis();
            loadDictionary("/dictionary-yawl.txt");
            log.info("dictionary-yawl.txt loaded in {} ms - {} total words",
                    System.currentTimeMillis() - start, words.size());
        }
        return Collections.unmodifiableSet(words);
    }
}
