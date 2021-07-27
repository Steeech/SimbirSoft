package com.example.service;

import com.example.model.OperationModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

@Service
public class Statistic {

    public static TreeMap<String, Integer>  analyzeText(List<String> words){
        TreeMap<String, Integer> dictionary = new TreeMap<>();
        for (String word: words){
            dictionary.merge(word, 1, Integer::sum);
        }
        return dictionary;
    }

    public String getStatistic(OperationModel model){
        String statistic = "";
        List<String> results = HtmlLoader.parseHTML(model.getDocument());
        TreeMap<String, Integer> dictionary = Statistic.analyzeText(results);
        for (HashMap.Entry result : dictionary.entrySet()) {
            statistic += result.getKey() + " - " + result.getValue() + "\n";
        }
        model.setStatistic(statistic);
        return statistic;
    }

}
