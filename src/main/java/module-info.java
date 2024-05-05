module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.logging;
    requires sdk.core;
    requires language.translator;
    requires text.to.speech;
    requires java.desktop;
    requires freetts;
    requires voicerss.tts;
    requires jsapi;

    opens com.example.dictionary to javafx.fxml;
    exports com.example.dictionary;
}