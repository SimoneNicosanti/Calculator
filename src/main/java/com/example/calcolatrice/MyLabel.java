package com.example.calcolatrice;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class MyLabel extends Label {

    public static final String LABEL_FONT = "Times New Roman Bold" ;
    public static final int LABEL_TEXT_SIZE = 20 ;
    public static final int LABEL_PADDING = 10 ;

    public MyLabel(String id, String text) {
        this.setId(id) ;
        this.setText(text);
        this.setWrapText(true);
        this.setFont(new Font(LABEL_FONT, LABEL_TEXT_SIZE));
        this.setPadding(new Insets(LABEL_PADDING)) ;
    }
}
