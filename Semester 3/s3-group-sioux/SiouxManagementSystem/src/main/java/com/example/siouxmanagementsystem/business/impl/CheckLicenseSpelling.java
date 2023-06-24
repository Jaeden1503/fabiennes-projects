package com.example.siouxmanagementsystem.business.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

@Service
public class CheckLicenseSpelling {

    //editing the wrong letter(s)
    private final ArrayList<String> edits(String word) {
        ArrayList<String> result = new ArrayList<String>();
        for(int i=0; i < word.length(); ++i) result.add(word.substring(0, i) + word.substring(i+1));
        for(int i=0; i < word.length()-1; ++i) result.add(word.substring(0, i) + word.substring(i+1, i+2) + word.substring(i, i+1) + word.substring(i+2));
        for(int i=0; i < word.length(); ++i) for(char c='A'; c <= 'Z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i+1));
        for(int i=0; i <= word.length(); ++i) for(char c='A'; c <= 'Z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i));

        for(int i=0; i < word.length(); ++i) for(char c='0'; c <= '9'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i+1));
        for(int i=0; i <= word.length(); ++i) for(char c='0'; c <= '9'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i));

        return result;
    }

    //check if it can find a 100% match otherwise check each letter to find the non-matching one
    public final String correct(String word, HashMap<String, Integer> nWords) {
        if(nWords.containsKey(word)) return word;
        ArrayList<String> list = edits(word);
        HashMap<Integer, String> candidates = new HashMap<Integer, String>();
        for(String s : list) if(nWords.containsKey(s)) candidates.put(nWords.get(s),s);
        if(candidates.size() > 0) return candidates.get(Collections.max(candidates.keySet()));
        for(String s : list) for(String w : edits(s)) if(nWords.containsKey(w)) candidates.put(nWords.get(w),w);
        return candidates.size() > 0 ? candidates.get(Collections.max(candidates.keySet())) : word;
    }
}
