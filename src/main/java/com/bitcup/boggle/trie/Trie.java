package com.bitcup.boggle.trie;

/**
 * Based on:
 * http://www.geekviewpoint.com/java/trie/is_prefix
 * http://www.geekviewpoint.com/java/trie/is_word
 * http://www.geekviewpoint.com/java/trie/add_word
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode('\0');
    }

    public boolean isWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.getNextNode(c);
            if (node == null) {
                return false;
            }
        }
        return node.isWord();
    }

    public boolean isPrefix(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.getNextNode(c);
            if (node == null) {
                return false;
            }
        }
        return node.hasNext();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            TrieNode next = node.getNextNode(c);
            if (next == null) {
                next = new TrieNode(c);
                node.setChild(c, next);
            }
            node = next;
        }
        if (node.isWord()) {
            return;
        }
        node.setWord(true);
    }
}
