package com.example.dictionary;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;



public class Online {
    @FXML
    private TextArea inputWord;
    @FXML
    private TextArea resultTxt;
    @FXML
    private TextField modeText;
    @FXML
    private ToggleButton modeButton;

    public static String modeTrans;
    public void initialize() {
        modeTrans = "en-vi";
        inputWord.setWrapText(true);
        modeText.setEditable(false);
        resultTxt.setWrapText(true);
        resultTxt.setEditable(false);
        modeText.setText("ENG to VIE");
    }

    public void setModeTrans(MouseEvent event) {
        if (modeButton.isSelected()) {
            modeTrans = "vi-en";
            modeText.setText("VIE to ENG");
            inputWord.setText("");
            resultTxt.setText("");
        } else {
            modeTrans = "en-vi";
            modeText.setText("ENG to VIE");
            inputWord.setText("");
            resultTxt.setText("");
        }
    }

    public void onlineSearch(MouseEvent event) throws Exception {
        String translateTxt = inputWord.getText().trim();
        if (translateTxt != null && translateTxt.length() >0) {
            if(modeTrans == "en-vi") {
                String res = DictionaryManagement.getOnline("en", "vi", translateTxt);
                resultTxt.setText(res);
            } else {
                String res = DictionaryManagement.getOnline("vi", "en", translateTxt);
                resultTxt.setText(res);
            }
        }
    }

    public void voice(MouseEvent event) throws Exception {
        String temp = inputWord.getText().trim();
        if (temp.length() > 0 && temp != null) {
            if (modeTrans.equals("en-vi")) {
                DictionaryCommandline.textToSpeech(temp);
            }
        }
    }
    public void voice2(MouseEvent event) throws Exception {
        String temp = resultTxt.getText().trim();
        if (temp.length() > 0 && temp != null) {
            if (modeTrans.equals("vi-en")) {
                DictionaryCommandline.textToSpeech(temp);
            }
        }
    }

}
