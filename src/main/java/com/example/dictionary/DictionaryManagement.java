package com.example.dictionary;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DictionaryManagement {


    public static void insertFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\dictionary\\engToVieCleanned.txt"));// Infile
            String line = reader.readLine();
            while (line != null) {
                int indexOfTab = line.indexOf("/");
                if (indexOfTab > 1) {
                    Word newWord = new Word(line.substring(1, indexOfTab - 1), line.substring(indexOfTab));
                    Dictionary.addNewWord(newWord);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred." + e);
        }
    }

    public static String dictionaryLookup(String word_) {
        if (word_ != null && word_.length() >0) {
            for (int i = 0; i < Dictionary.words.size(); i++) {
                if (word_.toLowerCase().equals(Dictionary.words.get(i).getWordTarget().toLowerCase())) {
                    return Dictionary.words.get(i).getWordExplain();
                }
            }
        }
        return "Can't find your word!";
    }

    public static void deleteWord(String word_) {
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (word_.equals(Dictionary.words.get(i).getWordTarget())) {
                Dictionary.words.remove(Dictionary.words.get(i));
            }
        }
    }

    public static void addNewWord(Word word) {
        Dictionary.words.add(word);
    }


    public static void dictionaryEdit(String oldWord, Word word_) {
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (Dictionary.words.get(i).getWordTarget().equals(oldWord)) {
                Dictionary.words.get(i).setWordTarget(word_.getWordTarget());
                Dictionary.words.get(i).setWordExplain(word_.getWordExplain());
                //return "Edited!";
            }
        }
        //return "Thất bại";
    }

    public static void dictionaryExportToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src\\main\\java\\com\\example\\dictionary\\engToVieCleanned.txt"); //Outfile
            for (int i = 0; i < Dictionary.words.size(); i++) {
                String line = "@" + Dictionary.words.get(i).getWordTarget() + " " + Dictionary.words.get(i).getWordExplain() + "\r\n";
                byte out[] = line.getBytes();
                fileOut.write(out);
            }
            fileOut.close();
        } catch (IOException e) {

            System.out.println("An error occurred." + e);
        }
    }

    public static String makeTrueForm(String str) {
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '*' || str.charAt(i) == '-') {
                res += "\r\n";
            }
            res += str.charAt(i);
        }
        return res;
    }


    public static String getOnline(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbw6i36gmKqj7ByIICkKf5mXOJNDPnv_BdnPvxk8ue8W6otjUMZzvGHG14VxjTUm9nw3jg/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}
