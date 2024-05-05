package com.example.dictionary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.Random;

public class Game {
    @FXML
    private Button Button1;
    @FXML
    private Button Button2;
    @FXML
    private Button Button3;
    @FXML
    private Button Button4;

    @FXML
    private TextArea consoleLog;
    @FXML
    private Label Question;
    private String ans;

    private int num;

    public void initialize(){
        int numOfQuestions = getNumOfQuestions();
        num = numOfQuestions;
        String line = choose(num);
        loadQuestion(line);
    }

    public static int getNumOfQuestions() {
        int numOfQuestions = 0;
        File file = new File("src\\main\\java\\com\\example\\dictionary\\game1.txt");

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));

            while ((br.readLine()) != null) {
                numOfQuestions++;
            }
            br.close();
            return numOfQuestions;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.toString());
        } catch (IOException e) {
            System.out.println("Unable to read file: " + file.toString());
        }
        return 0;
    }

    public void loadQuestion(String line) {
        String question = "", A = "", B = "", C = "", D = "";
        int currentTab = 0, numTab = 0, setTab = 0;
        for (int i = 0; i< line.length(); i++) {
            if (line.charAt(i) == '\t') {
                setTab = i;
                if (numTab == 0) {
                    question = new String(line.substring(currentTab, setTab));
                    Question.setText(question);
                    numTab++;
                } else if (numTab == 1) {
                    A = new String(line.substring(currentTab, setTab));
                    Button1.setText(A);
                    numTab++;
                } else if (numTab == 2) {
                    B = new String(line.substring(currentTab, setTab));
                    Button2.setText(B);
                    numTab++;
                } else if (numTab == 3) {
                    C = new String(line.substring(currentTab, setTab));
                    Button3.setText(C);
                    numTab++;
                } else if (numTab == 4) {
                    D = new String(line.substring(currentTab, setTab));
                    Button4.setText(D);
                    ans = new String(line.substring(setTab + 1));
                    break;
                }
                currentTab = setTab + 1;
            }
        }
    }

    public String choose(int numOfQuestions) {
        File file = new File("src\\main\\java\\com\\example\\dictionary\\game1.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            Random random = new Random();
            int randomInt = random.nextInt(numOfQuestions);
            int count=0;
            String icaocode;
            while ( (icaocode = br.readLine()) != null) {
                if (count == randomInt) {
                    br.close();
                    return icaocode;
                }
                count++;
            }
            br.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.toString());
        } catch (IOException e) {
            System.out.println("Unable to read file: " + file.toString());
        }
        return "";
    }

    public void Button1Pressed(MouseEvent event) {
        if(ans.equals("A")) {
            System.out.println("Correct");
            consoleLog.setText("Correct");
        } else {
            System.out.println("Incorrect");
            consoleLog.setText("Incorrect");
        }
    }

    public void Button2Pressed(MouseEvent event) {
        if(ans.equals("B")) {
            System.out.println("Correct");
            consoleLog.setText("Correct");
        } else {
            System.out.println("Incorrect");
            consoleLog.setText("Incorrect");
        }
    }

    public void Button3Pressed(MouseEvent event) {
        if(ans.equals("C")) {
            System.out.println("Correct");
            consoleLog.setText("Correct");
        } else {
            System.out.println("Incorrect");
            consoleLog.setText("Incorrect");
        }
    }

    public void Button4Pressed(MouseEvent event) {
        if(ans.equals("D")) {
            System.out.println("Correct");
            consoleLog.setText("Correct");
        } else {
            System.out.println("Incorrect");
            consoleLog.setText("Incorrect");
        }
    }

    public void refresh(MouseEvent event) {
        String line = choose(num);
        loadQuestion(line);
    }

}
