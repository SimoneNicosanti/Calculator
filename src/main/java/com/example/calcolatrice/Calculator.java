package com.example.calcolatrice;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;



public class Calculator extends Application {

    public static final String STAGE_NAME = "MyCalculator" ;

    public static final String FIRST_OPERAND_LABEL_ID = "firstOperandLabel" ;
    public static final String SECOND_OPERAND_LABEL_ID = "secondOperandLabel" ;
    public static final String OPERATION_LABEL_ID = "operationLabel" ;
    public static final String RESULT_LABEL_ID = "resultLabel" ;

    public static final List<String> OPERATION_STRING_ARRAY = List.of("+", "-", "x", "/", /*"^2",*/ "=") ;
    public static final List<String> MANAGE_STRING_ARRAY = List.of("<<", "Clear", "Ans",".", "(-)") ;
    //public static final List<String> SCIENTIFIC_OPERATION_STRING_ARRAY = List.of("Sin", "Cos", "Tan") ;

    public static final double PADDING = 5 ;


    @Override
    public void start(Stage stage) throws Exception {

        VBox labelVertBox = createLabelVertBox() ;
        HBox keyboardHorBox = createKeyboardHorBox() ;

        VBox groupVertBox = new VBox() ;
        groupVertBox.getChildren().addAll(labelVertBox, keyboardHorBox) ;
        Group mainGroup = new Group(groupVertBox) ;

        Scene myScene = new Scene(mainGroup) ;
        stage.setScene(myScene) ;
        stage.setTitle(STAGE_NAME);
        stage.setResizable(false);

        CalculatorController.getController().setScene(myScene) ;

        stage.show();
    }

    private HBox createKeyboardHorBox() {
        HBox keyboardHorBox = new HBox() ;

        HBox numberKeyboardBox = createNumberKeyboard() ;
        VBox operationKeyboardBox = createKeyboardColumn(OPERATION_STRING_ARRAY) ;
        VBox manageKeyboardBox = createKeyboardColumn(MANAGE_STRING_ARRAY) ;

        keyboardHorBox.getChildren().addAll(numberKeyboardBox, operationKeyboardBox, manageKeyboardBox) ;

        return keyboardHorBox ;
    }

    private VBox createKeyboardColumn(List array) {
        KeyboardButton[] buttonArray = new KeyboardButton[array.size()] ;
        for (int i = 0 ; i < array.size() ; i++ ) {
            KeyboardButton button = new KeyboardButton(array.get(i).toString()) ;
            buttonArray[i] = button ;

            button.setOnAction(CalculatorController.getController()) ;
        }

        return createVerticalBox(buttonArray) ;
    }


    private HBox createNumberKeyboard() {
        HBox numberKeyboard = new HBox() ;
        for (int i = 0 ; i < 3 ; i++) {
            numberKeyboard.getChildren().add(createNumberColumn(i)) ;
        }
        return numberKeyboard ;
    }

    private Node createNumberColumn(int index) {
        ArrayList<Integer> columnInt = new ArrayList<>() ;
        for (int j = 0 ; j < 3 ; j++) {
            columnInt.add((index + 1) + (j * 3)) ;
        }
        if (index == 1) {
            columnInt.add(0) ;
        }
        return createKeyboardColumn(columnInt.stream().toList()) ;
    }

    private VBox createLabelVertBox() {
        MyLabel firstOperandLabel = new MyLabel(FIRST_OPERAND_LABEL_ID, "");
        MyLabel operationLabel = new MyLabel(OPERATION_LABEL_ID, "");
        MyLabel secondOperandLabel = new MyLabel(SECOND_OPERAND_LABEL_ID, "");
        MyLabel resultLabel = new MyLabel(RESULT_LABEL_ID, "");

        VBox vertBox =  createVerticalBox(firstOperandLabel, operationLabel, secondOperandLabel, resultLabel) ;
        vertBox.setAlignment(Pos.CENTER);

        return vertBox ;
    }

    private VBox createVerticalBox(Node...nodes) {
        VBox vertBox = new VBox() ;
        for (Node node : nodes) {
            vertBox.getChildren().add(node) ;
        }
        vertBox.setPadding(new Insets(PADDING));
        vertBox.setSpacing(PADDING);
        return vertBox ;
    }

    public static void main(String[] args) {
        launch();
    }
}
