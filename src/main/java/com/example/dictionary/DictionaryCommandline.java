package com.example.dictionary;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DictionaryCommandline {

    public static List<Word> listOfWords = Dictionary.words;

    public static void showAllWords() {
        System.out.format("%-8s %-32s %-40s\n", "No", "|Vietnamese", "|English");
        for (int i = 0; i < listOfWords.size(); i++) {
            listOfWords.get(i).getWordInfo(i);
        }
    }


    public static boolean searchWord(String word) {
        for (int i=0;i<listOfWords.size();i++) {
            if (word.toLowerCase().equals(listOfWords.get(i).getWordTarget().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static List dictionarySearcher(String word) {
        List<String> searchList = new ArrayList<>();
        for (int i = 0; i < listOfWords.size(); i++) {
            if ((listOfWords.get(i).getWordTarget()).toLowerCase().indexOf(word.toLowerCase()) == 0) {
                searchList.add(listOfWords.get(i).getWordTarget());
            }
        }
        return searchList;
    }

    public static void textToSpeech(String str) {
        String text = str;

        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        try {
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.resume();

            synthesizer.speakPlainText(text, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
//            synthesizer.deallocate();
        } catch (EngineException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (AudioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (EngineStateError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
