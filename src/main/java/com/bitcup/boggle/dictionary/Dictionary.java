package com.bitcup.boggle.dictionary;

import java.util.Set;

public interface Dictionary {
    void loadDictionary(String name);
    Set<String> getAllWords();
}
