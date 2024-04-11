package com.webcrawler.service;

import org.springframework.stereotype.Service;

@Service
public class TrieService {

    //an efficient trie data structure for holding the urls
    //time complexity O(log(n))

    private TrieNode root = new TrieNode();

    public boolean isVisited(String url) {
        TrieNode current = root;
        for (char c : url.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return current.isEndOfWord;
    }

    public void markEndOfURL(String url) {
        TrieNode current = root;
        for (char c : url.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isEndOfWord = true;
    }
}
