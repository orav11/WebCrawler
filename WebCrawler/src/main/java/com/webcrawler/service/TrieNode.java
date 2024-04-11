package com.webcrawler.service;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
     Map<Character, TrieNode> children = new HashMap<>();
     boolean isEndOfWord;
}
