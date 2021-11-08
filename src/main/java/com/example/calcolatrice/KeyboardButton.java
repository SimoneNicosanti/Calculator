package com.example.calcolatrice;


import javafx.scene.control.Button;
import javafx.scene.text.Font;


import java.util.Map;

public class KeyboardButton extends Button {

    public static final int BUTTON_SIZE = 200 ;
    public static final String BUTTON_TEXT_FONT = "Times New Roman Bold" ;
    public static final int BUTTON_TEXT_SIZE = 25 ;

    private static final Map<String, String> MAPPING = Map.ofEntries(
            Map.entry("1", "One"),
            Map.entry("2", "Two"),
            Map.entry("3", "Three"),
            Map.entry("4", "Four"),
            Map.entry("5", "Five"),
            Map.entry("6", "Six"),
            Map.entry("7", "Seven"),
            Map.entry("8", "Eight"),
            Map.entry("9", "Nine"),
            Map.entry("0", "Zero"),

            Map.entry("+", "Plus"),
            Map.entry("-", "Minus"),
            Map.entry("x", "Per"),
            Map.entry("/", "Div"),
            Map.entry("=", "Equal"),
            Map.entry("^2", "Exp"),

            Map.entry("Clear", "Clear"),
            Map.entry("<<", "Del"),
            Map.entry("Ans", "Ans"),
            Map.entry(".", "Dot"),
            Map.entry("(-)", "Negate")) ;

    private static final String BUTTON_STRING = "button" ;

    public KeyboardButton() {
        this("") ;
    }

    public KeyboardButton(String buttonText) {
        super(buttonText) ;
        this.setMaxSize(BUTTON_SIZE,BUTTON_SIZE);
        this.setFont(new Font(BUTTON_TEXT_FONT, BUTTON_TEXT_SIZE));
        if (MAPPING.containsKey(buttonText)) {
            this.setId(BUTTON_STRING + MAPPING.get(buttonText));
        }

    }
}
