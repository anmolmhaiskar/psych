package com.psych.game;
import com.psych.game.Pair;
import com.psych.game.model.GameMode;
import com.psych.game.model.Question;

import com.sun.xml.bind.v2.runtime.SchemaTypeTransducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Utils {

    private static List<String> wordList;
    private static Map<String, Integer> wordIndices;

    static {
        wordList = new ArrayList<>();
        wordIndices = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("words.txt"));
        String word;

        do {
            word = br.readLine();
            wordList.add(word);
            wordIndices.put(word, wordList.size()-1);
        } while (word != null);
    }
    catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Pair<String,String>> readQAFile(String filename) throws FileNotFoundException {
        BufferedReader br =  new BufferedReader(new FileReader("qa_facts.txt"));
        String question,answer;
        List<Pair<String,String>> question_answer = new ArrayList<>();
        try {
            do {

                question = br.readLine();
                answer = br.readLine();
                if(question == null || answer == null || question.length() > Constants.MAX_QUESTION_LENGTH-1 || answer.length() > Constants.MAX_ANSWER_LENGTH-1) {
                    System.out.println("Skipping question "+question);
                    System.out.println("Skipping answer "+answer);
                    continue;
                }
                question_answer.add(new Pair<>(question,answer));
            }
            while (question != null && answer != null);
        }
        catch (IOException ignored){

        }
        return question_answer;
    }

    public static String getSecretCodeFromId(Long id) {
       StringBuilder code = new StringBuilder();
        int base = wordList.size();
        while(id > 0){
            code.append(wordList.get((int) (id%base)));
            id/=base;
        }
        return code.toString();
    }

    public static long getGameIdFromSecretCode(String code) {
        List<String> words = Arrays.asList(code.split(" " ));
        int base = wordList.size(),gameId=0;
        for(String word: words){
             gameId= base + wordIndices.get(word);
        }
        return gameId;
    }


}
