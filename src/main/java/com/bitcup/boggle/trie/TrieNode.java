package com.bitcup.boggle.trie;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Based on:
 * http://www.geekviewpoint.com/java/trie/trie_node
 */
@Data
class TrieNode {
    private char value;
    private boolean word = false;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<Character, TrieNode> next = new HashMap<>();

    TrieNode(char c) {
        value = c;
    }

    void setChild(char c, TrieNode node) {
        next.put(c, node);
    }

    boolean hasNext() {
        return !next.isEmpty();
    }

    TrieNode getNextNode(Character c) {
        return next.get(c);
    }

}
